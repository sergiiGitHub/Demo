package main.stack;

public class MinStack extends Stack {
	Stack minStack = null;
	
	public MinStack() {
		minStack = new Stack();
	}

	@Override
	public Integer pop() {
		Integer value = super.pop();
		if ( value == null ){
			return null;
		}
		if ( value == min() ){
			minStack.pop();
		}
		return value;
	}

	@Override
	public void push(int item) {
		if( item <= min() ){
			minStack.push(item);
		}
		super.push(item);
	}
	
	public int min() {
		if ( isEmpty() ){
			return Integer.MAX_VALUE;
		} else {
			return minStack.peek();
		}
	}

}
