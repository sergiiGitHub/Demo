package main.topping;

import main.decorator.base.BaseBeverage;
import main.decorator.base.BaseBeverageDecorator;

public class Whip extends BaseBeverageDecorator {
	private final int mCost = 2;
	private final String mDescription = " Whip :: ";
	
	public Whip(BaseBeverage aBaseBeverage) {
		super(aBaseBeverage);
	}

	public int getCost() {
		return getBeverage().getCost() + mCost;
	}

	public String getDescription() {
		return getBeverage().getDescription() + mDescription;
	}
}
