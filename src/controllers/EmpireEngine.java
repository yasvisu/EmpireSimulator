package controllers;

import contracts.Engine;
import contracts.Unit;
import contracts.UnitTree;
import contracts.UpgradeTypes;
import models.EmpireUnitTree;
import models.HugeInteger;

public class EmpireEngine implements Engine {

    private UnitTree bacon;
    private UnitTree freedom;
    private UnitTree democracy;
    private long moolah;
    private long elapsedSeconds;

    public EmpireEngine() {
		this.bacon = new EmpireUnitTree();
		this.freedom = new EmpireUnitTree();
		this.democracy = new EmpireUnitTree();
		this.moolah = 0;
		this.elapsedSeconds = 0;
    }

    @Override
    public void update() {

		long current = System.currentTimeMillis();
		long deltaT = current - this.elapsedSeconds * 1000;
		long deltaTSeconds = deltaT / 1000;
		if (deltaTSeconds > 0) {
	    /*
	     * Parse through all trees (or other types of data); update all
	     * trees.
	     * 
	     * Bacon: parse tree from root; increase descendants with output
	     * quantities. Freedom: parse tree from root; increase only Freedom
	     * at the bottom. Increase democracy by: (senators' output) *
	     * deltaTSeconds Money: do nothing (to be updated by dedicated
	     * methods for selling / buying)
	     */
			Unit currentBaconNode = this.bacon.getRootUnit();
			while(this.bacon.getChild(currentBaconNode) != null) {
				Unit child = this.bacon.getChild(currentBaconNode);
				HugeInteger increase = currentBaconNode.getOutputProduction();
				child.setPrecision(child.getPrecision() + increase.getPrecision());
				child.setExponent(child.getExponent() + increase.getExponent());
				currentBaconNode = child;
			}

			Unit currentFreedomNode = this.freedom.getRootUnit();
			while(this.freedom.getChild(currentFreedomNode) != null) {
				HugeInteger increase = currentFreedomNode.getOutputProduction();
				this.getResourceAmount(this.freedom).setPrecision(increase.getPrecision());
				this.getResourceAmount(this.freedom).setExponent(increase.getExponent());
				currentFreedomNode = this.freedom.getChild(currentFreedomNode);
			}

			HugeInteger currentDemocracy = this.getResourceAmount(this.democracy);
			Unit senator = this.democracy.getRootUnit(); // where is the Senator in the tree?
			HugeInteger democracyIncrease = new HugeInteger(
					senator.getOutputProduction().getPrecision() * deltaTSeconds % 10,
					(int) (senator.getOutputProduction().getExponent() * deltaTSeconds / (deltaTSeconds % 10)));
			currentDemocracy.setPrecision(currentDemocracy.getPrecision() + democracyIncrease.getPrecision());
			currentDemocracy.setExponent(currentDemocracy.getExponent() + democracyIncrease.getExponent());
		}
    }

    @Override
    public UnitTree getUnits(String resourceName) {
	switch (resourceName) {
		case "bacon":
			return this.bacon;
		case "freedom":
			return this.freedom;
		case "democracy":
			return this.democracy;
		default:
			throw new IllegalArgumentException("No such resource.");
		}
    }

	/*
	 * Check whether unit can be upgraded. Use
	 * Unit.getUpgradeLevel(upgradeType); Use
	 * this.calculateUpgradeCost(upgradeType, level); if
	 * Unit.getExponent()== cost exponent AND Unit.getPrecision() > cost
	 * precision => true else false
	 */
    @Override
    public boolean peekUpgrade(Unit unit, UpgradeTypes upgradeType) {
		if (unit == null) {
			throw new IllegalArgumentException("Unit to check upgrades for should not be null.");
		}

		int upgradeLevel = unit.getUpgradeLevel(upgradeType);
		HugeInteger upgradeCost = unit.calculateUpgradeCost(upgradeType, upgradeLevel + 1);
		if (unit.getExponent() >= upgradeCost.getExponent() && unit.getPrecision() > upgradeCost.getPrecision()) {
			return true;
		}

		return false;
    }

	/*
	 * Use peekUpgrade(unit, upgradeType) to see if an upgrade is possible.
	 * If true, set output with Unit.setOutput(number), or set spawncount
	 * with Unit.setSpawnCount(number). Use
	 * this.calculateUpgradeCost(upgradeType, level) to get the costs, then
	 * subtract the costs from the appropriate units. Hint: Get the
	 * "sacrifice unit" via previous unit.
	 */
    @Override
    public boolean upgrade(Unit unit, UpgradeTypes upgradeType) {
		boolean canUpgrade = this.peekUpgrade(unit, upgradeType);
		if (canUpgrade) {
			HugeInteger upgradeCost = unit.calculateUpgradeCost(upgradeType, unit.getUpgradeLevel(upgradeType));
			// value to be edited later
			unit.setOutputProduction(new HugeInteger(unit.getOutputProduction().getPrecision() * 2, unit.getExponent()));
			if (this.bacon.contains(unit)) {
				Unit child = this.bacon.getChild(unit);
				child.setExponent(child.getExponent() - upgradeCost.getExponent()); // subtract child unit cost
				child.setPrecision(child.getPrecision() - upgradeCost.getPrecision());
				unit.setUpgradeLevel(upgradeType, unit.getUpgradeLevel(upgradeType) + 1);
				return true;
			} else if (this.freedom.contains(unit)) {
				Unit child = this.freedom.getChild(unit);
				child.setExponent(child.getExponent() - upgradeCost.getExponent());
				child.setPrecision(child.getPrecision() - upgradeCost.getPrecision());
				unit.setUpgradeLevel(upgradeType, unit.getUpgradeLevel(upgradeType) + 1);
				return true;
			} else if (this.democracy.contains(unit)) {
				Unit child = this.democracy.getChild(unit);
				child.setExponent(child.getExponent() - upgradeCost.getExponent());
				child.setPrecision(child.getPrecision() - upgradeCost.getPrecision());
				unit.setUpgradeLevel(upgradeType, unit.getUpgradeLevel(upgradeType) + 1);
				return true;
			}
		}

		return false;
    }

	/*
	 * Use peekBuyUnits(unit, count) to see if an purchase is possible. If
	 * true, calculate # of new Units via count * Unit.getSpawnCount(). Then
	 * modify the precision and exponent. Use
	 * this.calculateUpgradeCost(upgradeType, level) to get the costs, then
	 * subtract the costs from the appropriate units. Hint: Get the
	 * "sacrifice unit" via previous unit.
	 */
    @Override
    public boolean peekBuyUnits(Unit unit, HugeInteger count) {
		HugeInteger totalUnitCost = new HugeInteger(unit.getUnitCost().getPrecision() * count.getPrecision(),
			unit.getExponent() * count.getExponent());
		HugeInteger totalResourceCost = new HugeInteger(unit.getResourceCost().getPrecision() * count.getPrecision(),
			unit.getExponent() * count.getExponent());

		if (this.bacon.contains(unit)) {
			Unit child = this.bacon.getChild(unit);
			HugeInteger totalChildAmount = new HugeInteger(child.getPrecision(), child.getExponent());
			if (totalChildAmount.compareTo(totalUnitCost) >= 0 &&
					this.getResourceAmount(this.bacon).compareTo(totalResourceCost) >= 0) {
				return true;
			}
		} else if (this.freedom.contains(unit)) {
			Unit child = this.freedom.getChild(unit);
			HugeInteger totalChildAmount = new HugeInteger(child.getPrecision(), child.getExponent());
			if (totalChildAmount.compareTo(totalUnitCost) >= 0 &&
					this.getResourceAmount(this.freedom).compareTo(totalResourceCost) >= 0) {
				return true;
			}
		} else if (this.democracy.contains(unit)) {
			Unit child = this.democracy.getChild(unit);
			HugeInteger totalChildAmount = new HugeInteger(child.getPrecision(), child.getExponent());
			if (totalChildAmount.compareTo(totalUnitCost) >= 0 &&
					this.getResourceAmount(this.democracy).compareTo(totalResourceCost) >= 0) {
				return true;
			}
		}

		return false;
    }


    @Override
    public boolean buyUnits(Unit unit, HugeInteger count) {
		if (this.peekBuyUnits(unit, count)) {
			HugeInteger totalCost = new HugeInteger(unit.getPrecision() * count.getPrecision(),
					unit.getExponent() * count.getExponent());

			if (this.bacon.contains(unit)) {
				HugeInteger newResourceAmount = new HugeInteger(
						this.getResourceAmount(this.bacon).getPrecision() - totalCost.getPrecision(),
						this.getResourceAmount(this.bacon).getExponent() - count.getExponent());
				this.setResourceAmount(this.bacon, newResourceAmount);

				HugeInteger newChildAmount = new HugeInteger(
						this.getResourceAmount(this.bacon).getPrecision() - unit.getUnitCost().getPrecision(),
						this.getResourceAmount(this.bacon).getExponent() - unit.getUnitCost().getExponent());
				this.bacon.getChild(unit).setPrecision(newChildAmount.getPrecision());
				this.bacon.getChild(unit).setExponent(newChildAmount.getExponent());
			} else if (this.freedom.contains(unit)) {
				HugeInteger newResourceAmount = new HugeInteger(
						this.getResourceAmount(this.freedom).getPrecision() - totalCost.getPrecision(),
						this.getResourceAmount(this.freedom).getExponent() - count.getExponent());
				this.setResourceAmount(this.freedom, newResourceAmount);

				HugeInteger newChildAmount = new HugeInteger(
						this.getResourceAmount(this.freedom).getPrecision() - unit.getUnitCost().getPrecision(),
						this.getResourceAmount(this.freedom).getExponent() - unit.getUnitCost().getExponent());
				this.freedom.getChild(unit).setPrecision(newChildAmount.getPrecision());
				this.freedom.getChild(unit).setExponent(newChildAmount.getExponent());
			} else if (this.democracy.contains(unit)) {
				HugeInteger newResourceAmount = new HugeInteger(
						this.getResourceAmount(this.democracy).getPrecision() - totalCost.getPrecision(),
						this.getResourceAmount(this.democracy).getExponent() - count.getExponent());
				this.setResourceAmount(this.democracy, newResourceAmount);

				HugeInteger newChildAmount = new HugeInteger(
						this.getResourceAmount(this.democracy).getPrecision() - unit.getUnitCost().getPrecision(),
						this.getResourceAmount(this.democracy).getExponent() - unit.getUnitCost().getExponent());
				this.democracy.getChild(unit).setPrecision(newChildAmount.getPrecision());
				this.democracy.getChild(unit).setExponent(newChildAmount.getExponent());
			}

			unit.setPrecision(unit.getPrecision() + count.getPrecision());
			unit.setExponent(unit.getExponent() + count.getExponent());
			return true;
		}

		return false;
    }

	/*
	 * Leap the engine by a certain amount of seconds:
	 *
	 * 1. Decrease this.elapsedSeconds by the amount of seconds
	 *
	 * 2. this.update();
	 *
	 */
    @Override
    public void leapSeconds(long seconds) {
		this.elapsedSeconds -= seconds;
		this.update();
    }

	/*
	 * Calculate the score of the player, based on:
	 *
	 * - Resources - Units - Upgrades
	 *
	 * (you decide how to weigh each)
	 *
	 */
    @Override
    public long getScore() {
		long score = 0;
		score += Math.sqrt(this.moolah); // to be balanced once game is working
		score += Math.sqrt(this.getResourceAmount(this.bacon).getExponent());
		score += Math.sqrt(this.getResourceAmount(this.democracy).getExponent());
		score += Math.sqrt(this.getResourceAmount(this.freedom).getExponent());
		return score;
    }

	private HugeInteger getResourceAmount(UnitTree tree) {
		HugeInteger resourceAmount = new HugeInteger(0, 0);
		Unit child = tree.getRootUnit();
		do {
			if (tree.getChild(child) != null) {
				child = tree.getChild(child);
			} else {
				resourceAmount.setExponent(child.getExponent());
				resourceAmount.setPrecision(child.getPrecision());
				return resourceAmount;
			}
		} while(true);
	}

	private void setResourceAmount(UnitTree tree, HugeInteger amount) {
		Unit child = tree.getRootUnit();
		do {
			if (tree.getChild(child) != null) {
				child = tree.getChild(child);
			} else {
				child.setPrecision(amount.getPrecision());
				child.setExponent(amount.getExponent());
			}
		} while(true);
	}
}
