import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample {

	public void run() {
		testQueue();
		System.out.println("--------------");
		// test Priority queue
		testPriorityQueue();

		// test Priority queue
		System.out.println("--------------");
		testMyLinkedQueue();
		// todo with priority queue
		System.out.println("--------------");
		testMyPriorityQueue();
	}

	// test queueLL
	// insert order
	// out 54321
	private void testQueue() {
		Queue<Integer> queueLL = new LinkedList<>();
		for (int i = 5; i > 0; --i) {
			queueLL.add(i);
		}

		while (!queueLL.isEmpty()) {
			System.out.print(queueLL.poll());
		}
		System.out.println();
	}

	// test Priority queue
	// insert order
	// out 12345
	private void testPriorityQueue() {
		Queue<Integer> queuePQ = new PriorityQueue<>();
		for (int i = 5; i > 0; --i) {
			queuePQ.add(i);
		}

		while (!queuePQ.isEmpty()) {
			System.out.print(queuePQ.poll());
		}
		System.out.println();
	}

	// test My Priority queue
	// in 5 2 1 4
	// must firstly even than odd
	// for Example 2 4 1 5
	//
	private void testMyLinkedQueue() {
		LinkedList<Integer> ll = new LinkedList<>();
		for (int i = 5; i > 0; --i) {
			if (i % 2 == 0) {
				ll.addFirst(i);
			} else {
				ll.addLast(i);
			}
		}

		while (!ll.isEmpty()) {
			System.out.print(ll.poll());
		}
		System.out.println();
	}

	// test My Priority queue
	// in 5 2 1 4
	// must firstly even than odd
	// for Example 2 4 1 5
	//
	private void testMyPriorityQueue() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(5,
				new Comparator<Integer>() {

					@Override
					public int compare(Integer left, Integer right) {
						if ((left % 2) == (right % 2)) {
							return left - right;
						} else if (left % 2 == 0) {
							return -1;
						} else {
							return 1;
						}

					}
				});
		for (int i = 5; i > 0; --i) {
			pq.add(i);
		}

		while (!pq.isEmpty()) {
			System.out.print(pq.poll());
		}
		System.out.println();
	}

	public static void main(String[] args) {
		new QueueExample().run();
	}
}