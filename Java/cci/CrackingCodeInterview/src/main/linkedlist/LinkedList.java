package main.linkedlist;

import java.util.Hashtable;

import main.core.Node;
/**
 * 
 * @author sergii
 * 
 * Example of usege
 *		LinkedList ll = new LinkedList();
 *	
 *			//add
 *			//ll.append(0);
 *			for ( int i = 0; i < 10; i++ ){
 *				ll.append(i);
 * 			}
 *			Node node = ll.getNode(5 );
 *			//System.out.println( ll.toString() );
 *			
 *			//ll.removeNode(node);
 *			ll.append( node );
 *			System.out.println( ll.getLoop() );
 * 			//System.out.println( ll.toString() );
 *
 */
public class LinkedList {
	private static final String SPACE = ", ";
	
	private Node head;
	
	public void append( int aData ){
		Node newNode = new Node(aData);
		append(newNode);
	}
	
	public void append(Node aNode) {
		if ( head == null ){
			head = aNode;
			return;
		}
		Node currentNode = head;
		while( currentNode.getNext() != null ) {
			currentNode = currentNode.getNext();
		}
		currentNode.setNext( aNode );	
	}
	
	public boolean remove( int aData ){
		boolean result = false; 
		if ( head == null ){ 
			return false;
		}

		if ( head.getData() == aData ){
			head = head.getNext();
			return true;
		}
		
		Node currentNode = head;
		while( currentNode.getNext() != null ) {
			Node next = currentNode.getNext();
			if( next.getData() == aData ){
				currentNode.setNext( next.getNext() );
				result = true;
				break;
			}
			currentNode = currentNode.getNext();
		}
		return result;
	}
	/**
	 * Remove duplicate value from linked list 
	 
	 * 
	 * main
	 * 		LinkedList ll = new LinkedList();

		//add
		ll.append(0);
		for ( int i = 0; i < 5; i++ ){
			if( i % 2 == 0 ){
				ll.append(i);
			}
			if ( i == 3 ){
				ll.append(i);
				ll.append(i);
				ll.append(i);
			}
			ll.append(i);
		}
		ll.append(0);
		System.out.println( ll.toString() );

		//delete
		//ll.remove(4);
		//ll.removeDublicateHashtable();
		ll.removeDublicateSmart();
		System.out.println( ll.toString() );
	 * 
	 * 
	 */
	/**
	 *
	 * With additional memory
	 * 
	 */
	public void removeDublicate(){
		if ( head == null ){ 
			return;
		}
		
		boolean check[] = new boolean[65535];

		Node currentNode = head;	
		while( currentNode != null && currentNode.getNext() != null ) {
			Node next = currentNode.getNext();
			if( !check[next.getData()] ){
				currentNode.setNext( next.getNext() );
				check[next.getData()] = true;
			}
			currentNode = currentNode.getNext();
		}
	}

	public void removeDublicateHashtable(){
		Hashtable<Integer, Boolean> hashtable = new Hashtable<>();
		
		Node currentNode = head;
		Node previuosNode = null;
		while( currentNode != null ) {
			if( hashtable.containsKey(currentNode.getData()) ){
				previuosNode.setNext(currentNode.getNext());
			} else {
				hashtable.put( currentNode.getData(), true );
				previuosNode = currentNode;
			}
			currentNode = currentNode.getNext();
		}
	}

	/**
	 * my
	 * Without using additional memory 
	 * 0010 - doesn't work
	 */
//	public void removeDublicateSmart(){
//		
//		Node externalNode = head;
//		while( externalNode != null ) {
//			Node currentNode = externalNode.getNext(); 
//			while( currentNode != null ) {
//				if( externalNode.getData() == currentNode.getData() ){
//					externalNode.setNext( externalNode.getNext().getNext() );
//				}
//				currentNode = currentNode.getNext();
//			}
//			externalNode = externalNode.getNext();
//		}
//	}
	/**
	 * From book
	 */
	public void removeDublicateBook() {

		if (head == null) return;

		Node previous = head;
		Node current = previous.getNext();
		while (current != null) {
			Node runner = head;
			while (runner != current) { // Check for earlier dups
				if (runner.getData() == current.getData()) {
					Node tmp = current.getNext(); // remove current
					previous.setNext( tmp );
					current = tmp; // update current to next node
					break; // all other dups have already been removed
				}
				runner = runner.getNext();
			}
			if (runner == current) { // current not updated - update now
				previous = current;
				current = current.getNext();
			}
		}
	}
	
	public void removeDublicateSmart(){

		Node externalNode = head;
		
		while( externalNode != null ) {
			Node previousNode = externalNode;
			Node currentNode = externalNode.getNext(); 
			while( currentNode != null ) {
				if( externalNode.getData() == currentNode.getData() ){
					if ( externalNode.getNext() == currentNode ){
						externalNode.setNext( externalNode.getNext().getNext() );
					}
					previousNode.setNext( currentNode.getNext() );
				}
				previousNode = currentNode;
				currentNode = currentNode.getNext();
			}
			externalNode = externalNode.getNext();
		}
	}
	
	public int findNthToEnd( int index ){
		Node currentNode = head;
		for( int i = 0; i < index+1; i++ ){
			if ( currentNode == null ){
				return -1;
			}
			currentNode = currentNode.getNext();
		}
		
		int result = 0;
		while( currentNode != null ){
			result++;
			currentNode = currentNode.getNext();
		}
		return result;
	}

	
	
	public void removeMiddle(){
		boolean isEven = true;
		
		Node currentNode = head;
		Node currentMiddle = null;
		
		while( currentNode.getNext() != null ){
			if ( isEven ){
				if ( currentMiddle != null ){
					currentMiddle = currentMiddle.getNext();
				}
				isEven = false;
			} else {
				isEven = true;
				if ( currentMiddle == null ){
					currentMiddle = head;
				}
			}
			currentNode = currentNode.getNext();
		}
		
		if ( currentMiddle != null ){
			currentMiddle.setNext( currentMiddle.getNext().getNext() );
		}
	}
	
	public boolean removeNode( Node aNode ){
		if ( aNode == null || aNode.getNext() == null ){
			return false;
		}
		
		Node nextNode = aNode.getNext();
		aNode.setData(nextNode.getData());
		aNode.setNext(nextNode.getNext());
		return true;
	}
	
	public Node getNode(int aData) {
		Node currentNode = head;
		while( currentNode != null ) {
			if ( currentNode.getData() == aData ){
				return currentNode;
			}
			currentNode = currentNode.getNext();
		}
		return null;
	}
	
	@Override
	public String toString() {
		
		if ( head == null ){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		Node currentNode = head;
		while( currentNode != null ) {
			sb.append(currentNode.getData()).append(SPACE);
			currentNode = currentNode.getNext();
		}
		return sb.toString();
	}

	/**
	 * My approach incorrect work with first and second node 
	 * @return
	 */
	public Node getLoop() {
		Node currentFast = head;
		Node currentSlow = head;
		while ( currentFast != null){
			currentFast = currentFast.getNext().getNext();
			currentSlow = currentSlow.getNext();
			if ( currentFast == currentSlow ){
				return currentFast;
			}
		}	
		return null;
	}
	/**
	 * solution from book
	 * @return
	 */
	public Node getLoopBook() {
		Node n1 = head;
		Node n2 = head;
		while ( n2.getNext() != null){
			n1 = n1.getNext();
			n2 = n2.getNext().getNext();
			if ( n1 == n2 ){
				break;
			}
		}
		
		if ( n2.getNext() == null ){
			return null;
		}
		
		n1 = head;
		while( n1 != n2 ){
			n1 = n1.getNext();
			n2 = n2.getNext();
		}
		return n2;
	}
	
}
