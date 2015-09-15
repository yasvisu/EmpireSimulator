package contracts;

import models.HugeInteger;

/**
 * Defines methods for accessing and manipulating an Engine.
 */
public interface Engine {
	
	/**
	 * Updates the engine.
	 */
	void update();
	
	/**
	 * Gets the UnitTree corresponding to the resource.
	 * @param resourceName	the resource to get
	 * @return				the resource's UnitTree
	 */
	UnitTree getUnits(String resourceName);

	/**
	 * Checks whether a unit can be upgraded.
	 * @param unit			the unit to upgrade
	 * @param upgradeType	the upgrade type
	 * @return				whether an upgrade can be made
	 */
	boolean peekUpgrade(Unit unit, UpgradeTypes upgradeType);

	/**
	 * Upgrades a given unit with a given upgradeType.
	 * @param unit			the unit to upgrade
	 * @param upgradeType	the upgrade type
	 * @return				whether the upgrade was successful
	 */
	boolean upgrade(Unit unit, UpgradeTypes upgradeType);

	/**
	 * Checks whether any units can be bought.
	 * @param unit	the unit to check
	 * @param count	the amount to check
	 * @return		whether any units can be bought
	 */
	boolean peekBuyUnits(Unit unit, HugeInteger count);

	/**
	 * Increases the count of a certain unit.
	 * @param unit	the unit to increase
	 * @param count	the amount to increase the unit by
	 * @return		whether any units were bought
	 */
	boolean buyUnits(Unit unit, int count);

	/**
	 * Leaps the engine by the given amount in seconds.
	 * @param seconds	the amount of seconds to leap
	 */
	void leapSeconds(long seconds);
	
	/**
	 * Returns the current score of the player.
	 * @return	the score of the player
	 */
	long getScore();

}
