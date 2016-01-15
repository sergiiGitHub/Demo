package main.pizza.base;

import main.pizza.BasePizza;
import main.pizza.EPizzaType;
import main.pizza.IngredientFactor;

public class Greek extends BasePizza {

	private static final EPizzaType type = EPizzaType.GREEK; 

	public Greek(IngredientFactor aIngredientFactor) {
		super(aIngredientFactor);
	}

	
	@Override
	public EPizzaType getType() {
		return type;
	}

}
