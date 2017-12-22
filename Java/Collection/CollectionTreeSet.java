import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class CollectionTreeMap {

	public static void main(String[] args) {
		NavigableSet<Integer> ss = new TreeSet<Integer>();
		for (int i = 0; i < 10; ++i) {
			ss.add(i);
		}

		// incorrect
		// System.out.println(getNext(ss, 9));
		// System.out.println(getPrevElements(ss, 9));

		// correct implementation
		System.out.println(ss.higher(9));
		System.out.println(ss.headSet(9));
	}

	private static Set<Integer> getPrevElements(SortedSet<Integer> ss, int i) {
		Set<Integer> res = new TreeSet();
		Iterator<Integer> it = ss.iterator();
		while (it.hasNext()) {
			int c = it.next();
			// System.out.println(c);
			if (c == i) {
				return res;
			}
			res.add(c);
		}

		res.clear();
		return null;
	}

	private static int getNext(SortedSet<Integer> ss, int i) {
		Iterator<Integer> it = ss.iterator();
		while (it.hasNext()) {
			int c = it.next();
			// System.out.println(c);
			if (c == i && it.hasNext()) {
				return it.next();
			}
		}
		return -1;
	}
}
