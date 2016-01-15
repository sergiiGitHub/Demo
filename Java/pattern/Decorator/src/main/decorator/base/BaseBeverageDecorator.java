package main.decorator.base;



public abstract class BaseBeverageDecorator extends BaseBeverage {
	
	private BaseBeverage mBeverage;
	
	public BaseBeverageDecorator( BaseBeverage aBaseBeverage ){
		this.mBeverage = aBaseBeverage;
	}
	
	protected BaseBeverage getBeverage(){
		return mBeverage;
	}
}
