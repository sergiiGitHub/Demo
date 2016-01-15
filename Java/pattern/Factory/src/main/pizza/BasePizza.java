package main.pizza;

public abstract class BasePizza implements IPizza {

	private final IngredientFactor ingredientFactor;
	
	public BasePizza( IngredientFactor aIngredientFactor ){
		ingredientFactor = aIngredientFactor;
	}
	
	@Override
	public void prepare() {
		System.out.print( getType().toString());
		System.out.print("prepare :: "); 
		System.out.print( ingredientFactor.createDough() );
		System.out.print( ingredientFactor.createSouce() );
		System.out.println();
	}

	@Override
	public void bake() {
		System.out.println( getType().toString() + "bake :: " );
	}

	@Override
	public void cut() {
		System.out.println( getType().toString() + "cut :: " );
	}

	@Override
	public void box() {
		System.out.println( getType().toString() + "box :: " );
	}
}
