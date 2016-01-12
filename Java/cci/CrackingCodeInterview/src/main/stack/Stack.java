package main.stack;

import main.core.Node;

public class Stack {
	Node top;
	public Integer pop() {
		if (top != null) {
			int item = top.getData();
			top = top.getNext();
			return item;
		}
		return null;
	}

	public void push(int item) {
		Node t = new Node(item);
		t.setNext( top );
		top = t;
	}
	
	public boolean isEmpty(){
		return top == null;
	}

	public int peek() {
		if ( isEmpty() ){
			return -1;
		}
		return top.getData();
	}
}
