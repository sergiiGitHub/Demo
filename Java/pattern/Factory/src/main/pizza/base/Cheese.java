package main.pizza.base;

import main.pizza.BasePizza;
import main.pizza.EPizzaType;
import main.pizza.IngredientFactor;

public class Cheese extends BasePizza {

	public Cheese(IngredientFactor aIngredientFactor) {
		super(aIngredientFactor);
	}

	private static final EPizzaType type = EPizzaType.CHEESE; 
	
	@Override
	public EPizzaType getType() {
		return type;
	}
}
