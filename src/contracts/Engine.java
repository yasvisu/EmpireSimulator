package contracts;

public interface Engine {
	void update();
	
	UnitTree getUnits(String resourceName);

	boolean peekUpgrade(Unit unit, UpgradeTypes upgradeType);

	boolean upgrade(Unit unit, UpgradeTypes upgradeType);

	boolean peekBuyUnits(Unit unit, int count);

	boolean buyUnits(Unit unit, int count);

	void leapSeconds(long seconds);

}
