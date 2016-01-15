package main.pizza.factory;

import main.pizza.EPizzaType;
import main.pizza.IPizza;
import main.pizza.base.Cheese;
import main.pizza.base.Greek;
import main.pizza.base.Paperony;

public class ChicagoPizzaStore extends PizzaStore {

	private final static String LOCATION = "Location: Chicago ";
	
	private final ChicagoIngredient chicagoIngredient;
	
	public ChicagoPizzaStore() {
		chicagoIngredient = new ChicagoIngredient();
	}

	@Override
	public IPizza createPizza(EPizzaType aType) {
		switch (aType) {
		case GREEK:
			return new Greek(chicagoIngredient);
		case PAPERONY:
			return new Paperony(chicagoIngredient);
		case CHEESE:
			return new Cheese(chicagoIngredient);
		default:
			return null;
		}
	}

	@Override
	public String getLocation() {
		return LOCATION;
	}
}
