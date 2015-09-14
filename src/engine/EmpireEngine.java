package engine;

import contracts.Engine;
import contracts.Unit;
import contracts.UnitTree;
import contracts.UpgradeTypes;
import models.EmpireUnitTree;

public class EmpireEngine implements Engine {

	private UnitTree bacon;
	private UnitTree freedom;
	private UnitTree democracy;
	private int baconLevel;
	private int freedomLevel;
	private int democracyLevel;
	private long money;

	public EmpireEngine() {
		this.bacon = new EmpireUnitTree();
		this.freedom = new EmpireUnitTree();
		this.democracy = new EmpireUnitTree();
		this.money = 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public UnitTree getUnits(String resourceName) {
		switch (resourceName) {
			case "bacon": return this.bacon;
			case "freedom": return this.freedom;
			case "democracy": return this.democracy;
			default: throw new IllegalArgumentException("No such resource.");
		}
	}

	@Override
	public boolean peekUpgrade(Unit unit, UpgradeTypes upgradeType) {
		if (unit == null) {
			throw new IllegalArgumentException("Unit to check upgrades for should not be null.");
		}


		int currentUnitLevel = unit.getLevel();
		if (upgradeType == UpgradeTypes.OutputUpgrade) {
			boolean someCondition = false;
			
			return someCondition;
		} else if (upgradeType == UpgradeTypes.SpawnCountUpgrade) {
			boolean otherCondition = false;
			return otherCondition;
		}

		return false;
	}

	@Override
	public boolean upgrade(Unit unit, UpgradeTypes upgradeType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean peekBuyUnits(Unit unit, int count) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buyUnits(Unit unit, int count) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void leapSeconds(long seconds) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getScore() {
	    // TODO Auto-generated method stub
	    return 0;
	}
}
