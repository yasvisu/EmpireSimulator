package controllers;

import contracts.Engine;
import contracts.Unit;
import contracts.UnitTree;
import contracts.UpgradeTypes;
import models.EmpireUnitTree;
import models.HugeInteger;

import java.util.Arrays;

public class EmpireEngine implements Engine {

    private UnitTree bacon;
    private UnitTree freedom;
    private UnitTree democracy;
    private long money;
    private long elapsedSeconds;

    public EmpireEngine() {
		this.bacon = new EmpireUnitTree();
		this.freedom = new EmpireUnitTree();
		this.democracy = new EmpireUnitTree();
		this.money = 0;
		this.elapsedSeconds = 0;
    }

    @Override
    public void update() {
	// TODO Auto-generated method stub

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

    @Override
    public boolean upgrade(Unit unit, UpgradeTypes upgradeType) {
		boolean canUpgrade = this.peekUpgrade(unit, upgradeType);
		if (canUpgrade) {
			HugeInteger upgradeCost = unit.calculateUpgradeCost(upgradeType, unit.getUpgradeLevel(upgradeType));
			unit.setOutputProduction(unit.getOutputProduction() * 2); // value to be edited later
			if (this.bacon.contains(unit)) {
				
			} else if (this.freedom.contains(unit)) {

			} else if (this.democracy.contains(unit)) {

			}
		}
	/*
	 * Use peekUpgrade(unit, upgradeType) to see if an upgrade is possible.
	 * If true, set output with Unit.setOutput(number), or set spawncount
	 * with Unit.setSpawnCount(number). Use
	 * this.calculateUpgradeCost(upgradeType, level) to get the costs, then
	 * subtract the costs from the appropriate units. Hint: Get the
	 * "sacrifice unit" via previous unit.
	 */

	return false;
    }

    @Override
    public boolean peekBuyUnits(Unit unit, int count) {
	// TODO Auto-generated method stub

	/*
	 * Calculate unit cost with count * Unit.getUnitCost() and count *
	 * Unit.getResourceCost(). If there are resources and enough units,
	 * return true. Hint: Get the "sacrifice unit" via Unit.getOutputUnits()
	 * else false
	 */

	return false;
    }

    @Override
    public boolean buyUnits(Unit unit, int count) {
	// TODO Auto-generated method stub

	/*
	 * Use peekBuyUnits(unit, count) to see if an purchase is possible. If
	 * true, calculate # of new Units via count * Unit.getSpawnCount(). Then
	 * modify the precision and exponent. Use
	 * this.calculateUpgradeCost(upgradeType, level) to get the costs, then
	 * subtract the costs from the appropriate units. Hint: Get the
	 * "sacrifice unit" via previous unit.
	 */

	return false;
    }

    @Override
    public void leapSeconds(long seconds) {
	// TODO Auto-generated method stub
	/*
	 * Leap the engine by a certain amount of seconds:
	 * 
	 * 1. DEcrease this.elapsedSeconds by the amount of seconds
	 * 
	 * 2. this.update();
	 * 
	 */
    }

    @Override
    public long getScore() {
	// TODO Auto-generated method stub
	/*
	 * Calculate the score of the player, based on:
	 * 
	 * - Resources - Units - Upgrades
	 * 
	 * (you decide how to weigh each)
	 * 
	 */
	return 0;
    }
}
