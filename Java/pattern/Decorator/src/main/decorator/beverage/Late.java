package main.decorator.beverage;

import main.decorator.base.BaseBeverage;

public class Late extends BaseBeverage {

	private final int mCost = 15;
	private final String mDescription = "Latte :: ";
	
	public int getCost() {
		return mCost;
	}

	public String getDescription() {
		return mDescription;
	}

	
}
