package main.pizza.factory;

import main.pizza.EPizzaType;
import main.pizza.IPizza;

public abstract class PizzaStore {

	public IPizza order( EPizzaType aType ){
		System.out.println( "\t Order" );
		System.out.println( getLocation() );
		IPizza pizza = createPizza(aType); 
//				mPizzaFactory.create( aType );
			
     	pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

	public abstract IPizza createPizza(EPizzaType aType);
	public abstract String getLocation();
}
