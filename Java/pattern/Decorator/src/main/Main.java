package main;

import main.decorator.base.BaseBeverage;
import main.decorator.beverage.Espresso;
import main.decorator.beverage.Late;
import main.topping.Milk;
import main.topping.Whip;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("main");
		
		BaseBeverage late = new Late();
		late = new Milk(late);
		late = new Whip(late);
		late.print();
		
		BaseBeverage espresso = new Espresso();
		espresso = new Milk(espresso);
		espresso = new Milk(espresso);
		espresso = new Whip(espresso);
		espresso.print();
	}
}


