package main.topping;

import main.decorator.base.BaseBeverage;
import main.decorator.base.BaseBeverageDecorator;

public class Milk extends BaseBeverageDecorator {

	private final int mMilkCost = 3;
	private final String mMilkDescription = " Milk :: ";
	
	public Milk(BaseBeverage aBaseBeverage) {
		super(aBaseBeverage);
	}

	public int getCost() {
		return getBeverage().getCost() + mMilkCost;
	}

	public String getDescription() {
		return getBeverage().getDescription() + mMilkDescription;
	}

}
