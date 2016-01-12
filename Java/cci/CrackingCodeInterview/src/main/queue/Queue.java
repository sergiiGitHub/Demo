package main.queue;

import main.core.Node;

public class Queue {

	Node first, last;

	void enqueue(Integer item) {
		if (first != null) {
			last = new Node(item);
			first = last;
		} else {
			first.setNext( new Node(item) );
			last = last.getNext();
		}
	}

	Object dequeue(Node n) {
		if (first != null) {
			Object item = first.getData();
			first = first.getNext();
			return item;
		}
		return null;
	}
}
