package models;

import java.util.LinkedList;

import contracts.Unit;

public abstract class EmpireUnit implements Unit {

	private double precision;
	private int exponent;
	private String flavorText;
	private int resourceCost;
	private int unitCost;
	private int outputProduction;
	LinkedList<Unit> outputUnits;

	protected EmpireUnit(double precision, int exponent, String flavorText, int resourceCost, int unitCost,
			int outputProduction, Unit... outputUnits) {
		this.setPrecision(precision);
		this.setExponent(exponent);
		this.setFlavorText(flavorText);
		this.setResourceCost(resourceCost);
		this.setUnitCost(unitCost);
		this.setOutputProduction(outputProduction);
		this.setOutputUnits(outputUnits);
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		// TODO Validate data

		this.precision = precision;
		this.balancePrecision();
	}

	public int getExponent() {
		return exponent;
	}

	public void setExponent(int exponent) {
		// TODO Validate data

		this.exponent = exponent;
	}

	public String getFlavorText() {
		return flavorText;
	}

	private void setFlavorText(String flavorText) {
		// TODO Validate data

		this.flavorText = flavorText;
	}

	public int getResourceCost() {
		return this.resourceCost;
	}

	private void setResourceCost(int resourceCost) {
		// TODO Validate data

		this.resourceCost = resourceCost;
	}

	public int getUnitCost() {
		return this.unitCost;
	}

	private void setUnitCost(int unitCost) {
		// TODO Validate data

		this.unitCost = unitCost;
	}

	public int getOutputProduction() {
		return this.outputProduction;
	}

	private void setOutputProduction(int outputProduction) {
		// TODO Validate data

		this.outputProduction = outputProduction;
	}

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

}
