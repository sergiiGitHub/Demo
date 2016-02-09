package main;

import oddevenlinkedlist_328.ListNode;
import oddevenlinkedlist_328.Solution;

public class Main {
	
	public static void main(String[] args) {
		
		Solution solution = new Solution();
		
		ListNode head = new ListNode(1); 
		
		ListNode currentNode = head;
		for( int i = 1; i < 5; i++ ){
			currentNode.next = new ListNode(1+i);
			currentNode = currentNode.next;
		}

		currentNode = head;
		while ( currentNode != null ){
			System.out.print( currentNode.value );
			System.out.print( ", " );
			currentNode = currentNode.next;
		}
		
		System.out.println( );
		
		currentNode = solution.oddEvenList(head);
		while ( currentNode != null ){
			System.out.print( currentNode.value );
			System.out.print( ", " );
			currentNode = currentNode.next;
		}
		System.out.println( "end" );
	}
}
