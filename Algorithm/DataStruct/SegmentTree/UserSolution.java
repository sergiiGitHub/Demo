public class UserSolution {

	private static final int MAX_VALUE = 8;// 1_000_000;
	private static SegmentTree st;

	public static void init() {
		st = new SegmentTree(MAX_VALUE);
	}

	public static int addEvent(int start, int end) {
		return st.add(start, end);
	}

	static class SegmentTree {
		Node head;

		SegmentTree(int capacity) {
			head = new Node(1, capacity);
		}

		public int add(int start, int end) {
			return head.add(start, end, 1);
		}
	}

	static class Node {
		int start, end;
		Node left, right;
		int count;

		Node(int al, int ar) {
			start = al;
			end = ar;
			// System.out.print("" + start + " > " + end + "\t");
			if (start == end) {
				return;
			}
			int m = al + (ar - al) / 2;
			left = new Node(al, m);
			right = new Node(m + 1, ar);

		}

		public int add(int as, int ae, int c) {
			if (as == start && ae == end && count != -1) {
				count += c;
				return count;
			} else {
				int prev = 0;
				if (count != -1) {
					prev = count;
					count = -1;
				}

				if (ae <= left.end) {
					return left.add(as, ae, prev + c);
				} else if (right.start <= as) {
					return right.add(as, ae, prev + c);
				} else {
					int resL = left.add(as, left.end, prev + c);
					int resR = right.add(right.start, ae, prev + c);
					return Math.max(resL, resR);
				}
			}
		}
	}
}
