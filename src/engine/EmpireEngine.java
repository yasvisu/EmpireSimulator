package engine;

import contracts.Engine;
import contracts.Unit;
import contracts.UnitTree;
import contracts.UpgradeTypes;

public class EmpireEngine implements Engine {

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public UnitTree getUnits(String resourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean peekUpgrade(Unit unit, UpgradeTypes upgradeType) {
		// TODO Auto-generated method stub
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
