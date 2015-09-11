package contracts;

public interface Unit {
	double getPrecision();

	int getExponent();

	int getResourceCost();

	int getUnitCost();

	int getOutputProduction();

	String getName();
	
	String getFlavorText();

	Unit[] getOutputUnits();

}
