package main.pizza.factory;

import main.pizza.IngredientFactor;

public class NYIngredient implements IngredientFactor {

	@Override
	public String createDough() {
		return "< NY Dough >";
	}

	@Override
	public String createSouce() {
		return " < NY Souce >";
	}

}
