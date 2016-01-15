package main.decorator.base;

public abstract class BaseBeverage {
	public abstract int getCost();
	public abstract String getDescription();
	public void print() {
		System.out.println( getDescription() + "\t; $ " + getCost() );
	}
}
	