package models;

public abstract class Unit {

	private double precision;
	private int exponent;
	private String flavorText;

	protected Unit(double precision, int exponent, String flavorText) {
		this.setPrecision(precision);
		this.setExponent(exponent);
		this.setFlavorText(flavorText);
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public int getExponent() {
		return exponent;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}

	public String getFlavorText() {
		return flavorText;
	}

	private void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

}
