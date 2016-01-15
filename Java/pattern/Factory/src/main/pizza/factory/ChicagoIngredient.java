package main.pizza.factory;

import main.pizza.IngredientFactor;

public class ChicagoIngredient implements IngredientFactor {

	@Override
	public String createDough() {
		return "< Chicago Dough >";
	}

	@Override
	public String createSouce() {
		return " < Chicago Souce >";
	}

}
