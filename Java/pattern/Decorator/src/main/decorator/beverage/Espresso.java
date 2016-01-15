package main.decorator.beverage;

import main.decorator.base.BaseBeverage;

public class Espresso extends BaseBeverage {

	private final int mCost = 11;
	private final String mDescription = "Espresso :: ";
	
	public int getCost() {
		return mCost;
	}

	public String getDescription() {
		return mDescription;
	}
}
