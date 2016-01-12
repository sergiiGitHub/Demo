package main.core;

public class Node {
	private int data;
	private Node next;
	
	public Node( int aData ) {
		setData(aData);
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return Integer.toString(getData());
	}
	
}
