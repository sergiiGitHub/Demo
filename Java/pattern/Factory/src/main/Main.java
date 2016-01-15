package main;

import main.pizza.EPizzaType;
import main.pizza.factory.ChicagoPizzaStore;
import main.pizza.factory.NYPizzaStore;
import main.pizza.factory.PizzaStore;

public class Main {
	
	public static void main(String[] args) {
		System.out.println( "main" );
		
		
		ChicagoPizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
		NYPizzaStore nyPizzaStor = new NYPizzaStore();
		
		chicagoPizzaStore.order(EPizzaType.CHEESE);
		nyPizzaStor.order(EPizzaType.GREEK);
	}
}
