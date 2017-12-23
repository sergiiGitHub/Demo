import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.WeakHashMap;

public class Main {
	private static final String IDS_INPUT_FILE_NAME = "sample.txt";
	private static final boolean DEBUG = false;

	public void run() {
		// check input access in HashMap
		// output {1=e, 2=d, 3=c, 4=b, 5=a}
		System.out.println("#HashMap: output: ");
		final HashMap<Integer, String> smth = new HashMap<>();
		for (int i = 5; i > 0; i--) {
			smth.put(i, String.valueOf((char) ('a' + 5 - i)));
		}
		System.out.println(smth);

		// check input access in LinkedHashMap
		// output {5=a, 4=b, 3=c, 2=d, 1=e}
		final HashMap<Integer, String> smth2 = new LinkedHashMap<>();
		for (int i = 5; i > 0; i--) {
			smth2.put(i, String.valueOf((char) ('a' + 5 - i)));
		}
		System.out.println("#LinkedHashMap: output: ");
		System.out.println(smth2);

		// check accessOrder true
		// output {4=b, 2=d, 3=c, 5=a, 1=e}
		// can be use for LRU cache + removeEldestEntry(entry<Key, Value>)
		final LinkedHashMap<Integer, String> smth3 = new LinkedHashMap(5, 1,
				true);

		for (int i = 5; i > 0; i--) {
			smth3.put(i, String.valueOf((char) ('a' + 5 - i)));
		}
		System.out.println("#LinkedHashMap(accessOrder = true): output: ");
		System.out.println(smth3);
		for (int i = 5; i > 0; i--) {
			smth3.get(3);
			smth3.get(5);
			smth3.get(1);
		}
		System.out.println(smth3);

		// implement LRE Cache
		final LinkedHashMap<Integer, String> lruExample = new SimpleLRUCache(2);

		for (int i = 3; i > 0; i--) {
			lruExample.put(i, String.valueOf((char) ('a' + 3 - i)));
		}
		System.out.println("#MyLinkedHashSet(LRU): output: ");
		System.out.println(lruExample);

		lruExample.get(2);
		lruExample.put(9, String.valueOf((char) ('a' + 9)));
		System.out.println(lruExample);

		// weakReference
		// for example isEmpty! i = 60
		// for temp save
		WeakHashMap<Integer, String> whm = new WeakHashMap<>();

		Integer n = new Integer(1);
		whm.put(n, "abc");

		n = null;
		System.gc();
		for (int i = 0; i < 10000; ++i) {
			if (whm.isEmpty()) {
				System.out.println("isEmpty! i = " + i);
				break;
			}
		}
	}

	static class SimpleLRUCache extends LinkedHashMap<Integer, String> {

		private final int capacity;

		public SimpleLRUCache(int capacity) {
			super(capacity + 1, 1.1f, true);
			this.capacity = capacity;
		}

		@Override
		protected boolean removeEldestEntry(
				java.util.Map.Entry<Integer, String> eldest) {
			return size() > capacity;// super.removeEldestEntry(eldest);
		}
	}

	private Scanner getScanner(boolean isDebug) {

		if (!isDebug) {
			return new Scanner(System.in);
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(IDS_INPUT_FILE_NAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return scanner;
	}

	public static void main(String[] args) {
		new Main().run();
	}
}