package main.pizza.factory;

import main.pizza.EPizzaType;
import main.pizza.IPizza;
import main.pizza.IngredientFactor;
import main.pizza.base.Cheese;
import main.pizza.base.Greek;
import main.pizza.base.Paperony;

public class NYPizzaStore extends PizzaStore {

	private final static String LOCATION = "Location: NY ";

	private final IngredientFactor nyIngredient;
	
	public NYPizzaStore() {
		nyIngredient = new NYIngredient();
	}
	
	@Override
	public IPizza createPizza(EPizzaType aType) {
		switch (aType) {
		case GREEK:
			return new Greek(nyIngredient);
		case PAPERONY:
			return new Paperony(nyIngredient);
		case CHEESE:
			return new Cheese(nyIngredient);
		default:
			return null;
		}
	}

	@Override
	public String getLocation() {
		return LOCATION;
	}
}
