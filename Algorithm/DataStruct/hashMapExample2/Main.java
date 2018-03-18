import java.awt.Point;
import java.util.Random;

public class Main {

	static final Random rnd = new Random(32);
	static final int SIZE = 16 * 4;
	static final int ZONE_SIZE = 4;
	static final int MAX_VALUE = 22;

	static int map[][];

	public static void print(int[][] map) {
		for (int y = 0; y < map.length; ++y) {
			for (int x = 0; x < map[y].length; ++x) {
				System.out.print(map[y][x] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		map = new int[SIZE][SIZE];
		int mapWithEmpty[][] = new int[SIZE][SIZE];
		int mapWEpostponned[][] = new int[SIZE][SIZE];
		int mapWEhash[][] = new int[SIZE][SIZE];

		for (int y = 0; y < SIZE; ++y) {
			for (int x = 0; x < SIZE; ++x) {
				map[y][x] = rnd.nextInt(MAX_VALUE - 1) + 1;
				if ((y < 2 || SIZE - 2 <= y) || (x < 2 || SIZE - 2 <= x)) {
					mapWithEmpty[y][x] = map[y][x];
					mapWEpostponned[y][x] = map[y][x];
					mapWEhash[y][x] = map[y][x];
				}
			}
		}

		testSimpleSolution(mapWithEmpty);

		testWithPostPonnedSolution(mapWEpostponned);

		testWithHashSolution(mapWEhash);

		// testLinkedList();
	}

	private static void testWithHashSolution(int[][] mapWithEmpty) {
		long start = System.currentTimeMillis();
		HashUserSoltion us = new HashUserSoltion();
		us.init(mapWithEmpty);
		check("testWithHashSolution:", mapWithEmpty, start, us.getCallColunt());
	}

	private static void testWithPostPonnedSolution(int[][] mapWithEmpty) {
		long start = System.currentTimeMillis();
		PostponedUserSoltion us = new PostponedUserSoltion();
		us.init(mapWithEmpty);
		check("testWithPostPonnedSolution:", mapWithEmpty, start,
				us.getCallColunt());
	}

	private static void testSimpleSolution(int mapWithEmpty[][]) {
		long start = System.currentTimeMillis();
		UserSoltion us = new UserSoltion();
		us.init(mapWithEmpty);
		check("testSimpleSolution:", mapWithEmpty, start, us.getCallColunt());
	}

	private static void check(String msg, int[][] mapWithEmpty, long start,
			int callCount) {
		for (int y = 0; y < SIZE; ++y) {
			for (int x = 0; x < SIZE; ++x) {
				if (map[y][x] != mapWithEmpty[y][x]) {
					System.out.println("Main map");
					print(map);
					throw new Error(msg = ": not rebuild; y:" + y + "; x: " + x);
				}
			}
		}
		System.out
				.println("Done: " + msg + " time: "
						+ (System.currentTimeMillis() - start) + "; call: "
						+ callCount);
	}

	private static void testLinkedList() {
		LinkedList<Integer> ll = new LinkedList<>();
		for (int i = 0; i < 5; ++i) {
			ll.apend(i);
		}

		System.out.println(ll);
		MyIterator<Integer> iterator = ll.iterator();
		while (iterator.hasNext()) {
			if (iterator.current() == 4 || iterator.current() == 2) {
				iterator.remove();
				System.out.println(ll);
			}
			iterator.next();
		}

		iterator = ll.iterator();
		while (iterator.hasNext()) {
			if (iterator.current() == 0 || iterator.current() == 3) {
				iterator.remove();
				System.out.println(ll);
			}
			iterator.next();
		}
		iterator = ll.iterator();
		while (iterator.hasNext()) {
			if (iterator.current() == 1) {
				iterator.remove();
				System.out.println(ll);
			}
			iterator.next();
		}
		System.out.println(ll);
	}

	private static int[][] getRndSquare4x4() {
		int[][] res = new int[ZONE_SIZE][ZONE_SIZE];
		int y = rnd.nextInt((SIZE - 1) / 2) * 2;
		int x = rnd.nextInt((SIZE - 1) / 2) * 2;

		for (int iy = y; iy < y + ZONE_SIZE; ++iy) {
			for (int ix = x; ix < x + ZONE_SIZE; ++ix) {
				res[iy - y][ix - x] = map[iy][ix];
			}
		}
		return res;
	}

	static class MyIterator<T> {

		Node<T> cur;
		Node<T> prev = null;
		LinkedList<T> linkedList;

		MyIterator(LinkedList<T> aLinkedList) {
			linkedList = aLinkedList;
			cur = linkedList.head;
		}

		public boolean hasNext() {
			return cur != null;
		}

		public void next() {
			prev = cur;
			if (cur != null) {
				cur = cur.next;
			}
		}

		public T current() {
			return cur == null ? null : cur.get();
		}

		public void remove() {
			if (cur == null) {
				return;
			}
			if (cur == linkedList.head) {
				linkedList.head = cur.next;
			} else {
				if (cur != null && prev != null) {
					prev.next = cur.next;
				}
			}
		}
	}

	static class Zone {
		int zone[][];

		Zone(int z[][]) {
			zone = z;
		}
	}

	static class Node<T> {
		T data;
		Node<T> next;

		public Node(T aZone) {
			data = aZone;
		}

		T get() {
			return data;
		}
	}

	static class LinkedList<T> {
		Node<T> head;

		void apend(T aZone) {
			Node<T> cur = head;
			head = new Node<T>(aZone);
			head.next = cur;
		}

		public MyIterator<T> iterator() {
			return new MyIterator<T>(this);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			Node<T> cur = head;
			sb.append('{');
			while (cur != null) {
				sb.append(cur.get());
				sb.append(", ");
				cur = cur.next;
			}
			sb.append('}');
			return sb.toString();
		}
	}

	static abstract class AbsUserSolution {
		protected int map[][];
		protected int empty;
		protected int count;

		abstract void init(int map[][]);

		public int getCallColunt() {
			return count;
		}

		protected boolean findAndFill(int[][] zone4x4) {
			for (int y = 0; y < SIZE - 2; y += 2) {
				for (int x = 0; x < SIZE - 2; x += 2) {
					if (isMatch(y, x, zone4x4)) {
						fill(y, x, zone4x4);
						return true;
					}
				}
			}
			return false;
		}

		protected Point find(int[][] zone4x4) {
			for (int y = 0; y < SIZE - 2; y += 2) {
				for (int x = 0; x < SIZE - 2; x += 2) {
					if (isMatch(y, x, zone4x4)) {
						fill(y, x, zone4x4);
						return new Point(x, y);
					}
				}
			}
			return null;
		}

		protected void fill(int y, int x, int[][] zone4x4) {

			for (int zy = 0; zy < ZONE_SIZE; ++zy) {
				for (int zx = 0; zx < ZONE_SIZE; ++zx) {
					if (map[y + zy][x + zx] == 0) {
						map[y + zy][x + zx] = zone4x4[zy][zx];
						--empty;
					}
				}
			}
		}

		protected boolean isMatch(int y, int x, int[][] zone4x4) {
			boolean isOnlyZero = true;
			for (int zy = 0; zy < ZONE_SIZE; ++zy) {
				for (int zx = 0; zx < ZONE_SIZE; ++zx) {
					if (map[y + zy][x + zx] == 0) {
						continue;
					}
					isOnlyZero = false;
					if (map[y + zy][x + zx] != zone4x4[zy][zx]) {
						return false;
					}
				}
			}
			return !isOnlyZero;
		}

	}

	static class UserSoltion extends AbsUserSolution {

		@Override
		void init(int map[][]) {
			count = 0;
			empty = (map.length - 4) * (map.length - 4);
			this.map = map;
			// print(map);

			while (0 < empty) {
				int zone4x4[][] = getRndSquare4x4();
				++count;
				// print(zone4x4);
				findAndFill(zone4x4);
			}
			System.out.println("After");
			// print(map);
		}
	}

	static class Key {
		int key[][];
		final int hashCode;

		Key(int key[][]) {
			this.key = key;
			hashCode = calc();
		}

		private int calc() {
			int hash = 1;
			for (int y = 0; y < key.length; ++y) {
				for (int x = 0; x < key[y].length; ++x) {
					hash *= 31;
					hash += key[y][x];
					hash %= ((Integer.MAX_VALUE - MAX_VALUE) / 31);
				}
			}
			return hash;
		}

		@Override
		public int hashCode() {
			return hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}

			if (hashCode != obj.hashCode() && obj instanceof Key) {
				Key other = (Key) obj;
				if (key.length != other.key.length) {
					return false;
				}
				for (int y = 0; y < key.length; ++y) {
					if (key[y].length != other.key[y].length) {
						return false;
					}
					for (int x = 0; x < key.length; ++x) {
						if (key[y][x] != other.key[y][x]) {
							return false;
						}
					}
				}
				return true;
			}
			return false;
		}
	}

	static class Entity {
		private Zone zone;
		private final Key key;

		Entity(Key key, Zone zone) {
			this.key = key;
			this.zone = zone;
		}
	}

	static class MyHashMap {
		LinkedList<Entity> ll[];

		MyHashMap(int capacity) {
			ll = new LinkedList[capacity];
		}

		void put(Key key, Zone zone) {
			int bIndex = key.hashCode % ll.length;
			if (ll[bIndex] == null) {
				ll[bIndex] = new LinkedList<>();
			}
			MyIterator<Entity> iter = ll[bIndex].iterator();
			while (iter.hasNext()) {
				Entity cur = iter.current();
				if (cur.key == key) {
					cur.zone = zone;
					return;
				}
				iter.next();
			}
			ll[bIndex].apend(new Entity(key, zone));
		}

		Zone remove(Key key) {
			int bIndex = key.hashCode % ll.length;
			if (ll[bIndex] != null) {
				MyIterator<Entity> iter = ll[bIndex].iterator();
				while (iter.hasNext()) {
					Entity cur = iter.current();
					if (cur.key == key) {
						iter.remove();
						return cur.zone;
					}
					iter.next();
				}
			}

			return null;
		}

		Zone get(Key key) {
			int bIndex = key.hashCode % ll.length;
			if (ll[bIndex] != null) {
				MyIterator<Entity> iter = ll[bIndex].iterator();
				while (iter.hasNext()) {
					Entity cur = iter.current();
					if (cur.key == key) {
						return cur.zone;
					}
					iter.next();
				}
			}

			return null;
		}
	}

	static class HashUserSoltion extends AbsUserSolution {

		MyHashMap hash;

		@Override
		void init(int map[][]) {
			hash = new MyHashMap(64 * 64);
			count = 0;
			empty = (map.length - 4) * (map.length - 4);
			this.map = map;
			// print(map);

			while (0 < empty) {
				int zone4x4[][] = getRndSquare4x4();
				++count;
				// print(zone4x4);

				if (findAndFill(zone4x4)) {
					// take from hash
					Zone rZone;
					Key createHT = createHT(zone4x4);
					rZone = hash.get(createHT);
					if (rZone != null && findAndFill(rZone.zone)) {
						hash.remove(createHT);
						continue;
					}
					Key createHB = createHB(zone4x4);
					rZone = hash.get(createHB);
					if (rZone != null && findAndFill(rZone.zone)) {
						hash.remove(createHB);
						continue;
					}

					Key createVL = createVL(zone4x4);
					rZone = hash.get(createVL);
					if (rZone != null && findAndFill(rZone.zone)) {
						hash.remove(createVL);
						continue;
					}

					Key createVR = createVR(zone4x4);
					rZone = hash.get(createVR);
					if (rZone != null && findAndFill(rZone.zone)) {
						hash.remove(createVR);
					}
				} else {
					// horizontal top
					Zone zone = new Zone(zone4x4);
					hash.put(createHT(zone4x4), zone);
					hash.put(createHB(zone4x4), zone);
					hash.put(createVL(zone4x4), zone);
					hash.put(createVR(zone4x4), zone);
				}
			}

			// print(map);
		}

		private Key createHT(int[][] zone4x4) {
			int key[][] = new int[2][4];
			for (int y = 0; y < key.length; ++y) {
				for (int x = 0; x < key[y].length; ++x) {
					key[y][x] = zone4x4[y][x];
				}
			}
			return new Key(key);
		}

		private Key createHB(int[][] zone4x4) {
			int key[][] = new int[2][4];
			for (int y = 0; y < key.length; ++y) {
				for (int x = 0; x < key[y].length; ++x) {
					key[y][x] = zone4x4[y + 2][x];
				}
			}
			return new Key(key);
		}

		private Key createVR(int[][] zone4x4) {
			int key[][] = new int[4][2];
			for (int y = 0; y < key.length; ++y) {
				for (int x = 0; x < key[y].length; ++x) {
					key[y][x] = zone4x4[y][x + 2];
				}
			}
			return new Key(key);
		}

		private Key createVL(int[][] zone4x4) {
			int key[][] = new int[4][2];
			for (int y = 0; y < key.length; ++y) {
				for (int x = 0; x < key[y].length; ++x) {
					key[y][x] = zone4x4[y][x];
				}
			}
			return new Key(key);
		}
	}

	static class PostponedUserSoltion extends AbsUserSolution {

		private LinkedList<Zone> ll;

		@Override
		void init(int map[][]) {
			ll = new LinkedList<Zone>();
			count = 0;
			empty = (map.length - 4) * (map.length - 4);
			this.map = map;
			// print(map);

			while (0 < empty) {
				int zone4x4[][] = getRndSquare4x4();
				++count;
				// print(zone4x4);
				if (findAndFill(zone4x4)) {
					MyIterator<Zone> iter = ll.iterator();
					while (iter.hasNext()) {
						if (findAndFill(iter.cur.data.zone)) {
							iter.remove();
						}
						iter.next();
					}
				} else {
					ll.apend(new Zone(zone4x4));
				}
			}

			// print(map);
		}
	}
}