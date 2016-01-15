package main.pizza;

public interface IPizza {
	EPizzaType getType(); 
	
	void prepare();
	void bake();
	void cut();
	void box();
}
