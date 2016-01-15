package main.pizza.base;

import main.pizza.BasePizza;
import main.pizza.EPizzaType;
import main.pizza.IngredientFactor;

public class Paperony extends BasePizza {

	private static final EPizzaType type = EPizzaType.PAPERONY; 
	
	public Paperony(IngredientFactor aIngredientFactor) {
		super(aIngredientFactor);
	}
	
	@Override
	public EPizzaType getType() {
		return type;
	}

}
