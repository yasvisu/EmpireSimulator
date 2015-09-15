package models;

import java.util.LinkedList;

import contracts.Unit;
import contracts.UpgradeTypes;

public abstract class EmpireUnit implements Unit {

	private double precision;
	private int exponent;
	private String name;
	private String flavorText;
	private int resourceCost;
	private int unitCost;
	private int outputProduction;
	private int level;
	LinkedList<Unit> outputUnits;

	protected EmpireUnit(double precision, int exponent,String name, String flavorText, int resourceCost, int unitCost,
			int outputProduction, Unit... outputUnits) {
		this.setPrecision(precision);
		this.setExponent(exponent);
		this.setName(name);
		this.setFlavorText(flavorText);
		this.setResourceCost(resourceCost);
		this.setUnitCost(unitCost);
		this.setOutputProduction(outputProduction);
		this.setOutputUnits(outputUnits);
	}

	@Override
	public double getPrecision() {
		return precision;
	}

	@Override
	public void setPrecision(double precision) {
		// TODO Validate data

		this.precision = precision;
		this.balancePrecision();
	}

	@Override
	public int getExponent() {
		return exponent;
	}

	@Override
	public void setExponent(int exponent) {
		// TODO Validate data

		this.exponent = exponent;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	private void setName(String name){
		this.name=name;
	}

	@Override
	public String getFlavorText() {
		return flavorText;
	}

	private void setFlavorText(String flavorText) {
		// TODO Validate data

		this.flavorText = flavorText;
	}

	@Override
	public int getResourceCost() {
		return this.resourceCost;
	}

	@Override
	public void setResourceCost(int resourceCost) {
		// TODO Validate data

		this.resourceCost = resourceCost;
	}

	@Override
	public int getUnitCost() {
		return this.unitCost;
	}

	@Override
	public void setUnitCost(int unitCost) {
		// TODO Validate data

		this.unitCost = unitCost;
	}

	@Override
	public int getOutputProduction() {
		return this.outputProduction;
	}

	@Override
	public void setOutputProduction(int outputProduction) {
		// TODO Validate data

		this.outputProduction = outputProduction;
	}

	@Override
	public Unit[] getOutputUnits() {
		return this.outputUnits.toArray(new Unit[this.outputUnits.size()]);
	}

	private void setOutputUnits(Unit... outputUnits) {
		// TODO Validate data

		this.outputUnits = new LinkedList<Unit>();
		for (Unit u : outputUnits) {
			this.outputUnits.add(u);
		}
	}

	private void balancePrecision() {
		while (this.precision >= 10) {
			this.precision /= 10;
			this.exponent++;
		}
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		if (level < 0) {
			throw new IllegalArgumentException("Level cannot be negative.");
		}

		this.level = level;
	}

	@Override
	public HugeInteger calculateUpgradeCost(UpgradeTypes upgradeType, int level) {
		switch (upgradeType) {
			case OutputUpgrade:
				return new HugeInteger(1, 2 * level); // random value, to be edited later
			case SpawnCountUpgrade:
				return new HugeInteger(2, 2 * level); // random value, to be edited later
			default:
				throw new IllegalArgumentException("No such upgrade type.");
		}
	}
}
