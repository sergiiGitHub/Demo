package main;

import main.stack.MinStack;

public class Main {
	public static void main(String[] args) {
		System.out.println("main");

		MinStack minStack = new MinStack();
		for ( int i = 0; i < 5; i++ ){
			minStack.push(i);
		}
		
		for ( int i = 0; i < 5; i++ ){
			System.out.println( "i = " + i + "; min = " + minStack.min());
			minStack.pop();
		}
	}

}
