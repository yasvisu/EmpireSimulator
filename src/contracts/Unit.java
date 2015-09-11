package contracts;

public interface Unit {
	double getPrecision();

	int getExponent();

	int getResourceCost();

	int getUnitCost();

	int getOutputProduction();

	String getFlavorText();

	Unit[] getOutputUnits();

}
