package oddevenlinkedlist_328;

public class Solution {
	public ListNode oddEvenList(ListNode head) {
		if ( head == null || head.next == null ){
			return head;
		}
		ListNode current = new ListNode(head.value);
		
		
		return current;
    }
	
//	public ListNode oddEvenList(ListNode head) {
//		if ( head == null || head.next == null ){
//			return head;
//		}
//		ListNode result = new ListNode(head.value);
//		ListNode odd = result;
//		while ( head != null && head.next != null ){
//			if ( head.next.next != null ){
//				odd.next = new ListNode( head.next.next.value );
//				head = head.next.next;
//			} else {
//				odd.next = new ListNode( head.next.value );
//				head = null;
//			}
//			odd = odd.next;
//			
//		}
//		return result;
//    }
}
