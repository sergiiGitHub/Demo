import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/*
Done: List: time: 4180
Done: HashMap: time: 1
Done: MyHashMap: time: 5
*/

public class Main {

	static final StringBuilder sb = new StringBuilder();
	static final Random random = new Random(32);
	static final int SIZE = 150_000;
	static final float COEF = 1 / 100F;

	static class Column {
		String strings[] = new String[5];
	}

	static class LocalCheck {
		Column column;
		int index;

		LocalCheck(Column aColumn, int aIndex) {
			column = aColumn;
			index = aIndex;
			broke(random.nextInt(column.strings.length - 2) + 1);
		}

		private void broke(int broken) {
			for (int i = 0; i < broken; ++i) {
				column.strings[i] = createString();
			}
		}
	}

	public static void main(String[] args) {

		final int saveEach = random.nextInt((int) (SIZE * COEF));
		int index = 0;
		IUsersolution us = new UserSolution();
		IUsersolution usHash = new UserSolutionHash();
		IUsersolution usHashMy = new UserSolutionMyHash();
		List<LocalCheck> list = new ArrayList<>(SIZE / saveEach);

		while (index < SIZE) {
			Column column = createColumn();
			us.addColumn(column);
			usHash.addColumn(column);
			usHashMy.addColumn(column);
			if (index % saveEach == 0) {
				list.add(new LocalCheck(column, index));
			}
			++index;
		}

		long start = System.currentTimeMillis();

		index = 0;
		for (LocalCheck lc : list) {
			// System.out.println(index++);
			int actual = us.getIndex(lc.column);
			if (actual != lc.index) {
				throw new Error("actual:" + actual + "; expected: " + lc.index);
			}
		}
		System.out.println("Done: List: time: "
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		index = 0;
		for (LocalCheck lc : list) {
			// System.out.println(index++);
			int actual = usHash.getIndex(lc.column);
			if (actual != lc.index) {
				throw new Error("actual:" + actual + "; expected: " + lc.index);
			}
		}
		System.out.println("Done: HashMap: time: "
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		index = 0;
		for (LocalCheck lc : list) {
			// System.out.println(index++);
			int actual = usHashMy.getIndex(lc.column);
			if (actual != lc.index) {
				throw new Error("actual:" + actual + "; expected: " + lc.index);
			}
		}
		System.out.println("Done: MyHashMap: time: "
				+ (System.currentTimeMillis() - start));

		// MyHashMap myHashMap = new MyHashMap(5);
		// ColumnHolder columnHolder = new ColumnHolder(0, createColumn());
		// myHashMap.put("a".toCharArray(), columnHolder);
		// myHashMap.put("b".toCharArray(), columnHolder);
		// for (String str : columnHolder.column.strings) {
		// myHashMap.put(str.toCharArray(), columnHolder);
		// }
		// System.out.println(myHashMap);
		// System.out.println(myHashMap.get("a".toCharArray()));
		//
		// System.out.println(myHashMap.get("c".toCharArray()));
		// System.out.println(myHashMap.get(columnHolder.column.strings[3]
		// .toCharArray()));

	}

	private static Column createColumn() {
		Column column = new Column();
		for (int i = 0; i < column.strings.length; ++i) {
			column.strings[i] = createString();
		}
		return column;
	}

	private static String createString() {
		sb.setLength(0);
		for (int i = 0; i < 10; ++i) {
			sb.append((char) (random.nextInt(26) + 'a'));
		}
		return sb.toString();
	}

	static class UserSolution implements IUsersolution {

		Column columns[] = new Column[SIZE];
		int count = 0;

		@Override
		public void addColumn(Column aColumn) {
			columns[count] = aColumn;
			++count;
		}

		@Override
		public int getIndex(Column aColumn) {
			for (int i = 0; i < count; ++i) {
				Column cur = columns[i];
				for (int j = 0; j < cur.strings.length; ++j) {
					if (compare(cur.strings[j], aColumn.strings[j])) {
						return i;
					}
				}
			}
			return -1;
		}
	}

	public interface IUsersolution {
		void addColumn(Column aColumn);

		int getIndex(Column column);
	}

	public static boolean compare(String left, String right) {
		for (int i = 0; i < left.length(); ++i) {
			if (left.charAt(i) != right.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	static class UserSolutionHash implements IUsersolution {

		HashMap<String, ColumnHolder> hashs = new HashMap<>(SIZE);
		int count = 0;

		@Override
		public void addColumn(Column aColumn) {
			for (String str : aColumn.strings) {
				hashs.put(str, new ColumnHolder(count, aColumn));
			}
			++count;
		}

		@Override
		public int getIndex(Column aColumn) {
			for (String str : aColumn.strings) {
				ColumnHolder holder = hashs.get(str);
				if (holder == null) {
					continue;
				}
				Column cur = holder.column;
				for (int j = 0; j < cur.strings.length; ++j) {
					if (compare(cur.strings[j], aColumn.strings[j])) {
						return holder.index;
					}
				}
			}
			return -1;
		}
	}

	static class ColumnHolder {
		Column column;
		int index;

		public ColumnHolder(int i, Column aColumn) {
			column = aColumn;
			index = i;
		}

		@Override
		public String toString() {
			return "" + index;
		}
	}

	static class UserSolutionMyHash implements IUsersolution {

		MyHashMap hashs = new MyHashMap(SIZE);
		int count = 0;

		@Override
		public void addColumn(Column aColumn) {
			for (String str : aColumn.strings) {
				hashs.put(str.toCharArray(), new ColumnHolder(count, aColumn));
			}
			++count;
		}

		@Override
		public int getIndex(Column aColumn) {
			for (String str : aColumn.strings) {
				ColumnHolder holder = hashs.get(str.toCharArray());
				if (holder == null) {
					continue;
				}
				Column cur = holder.column;
				for (int j = 0; j < cur.strings.length; ++j) {
					if (compare(cur.strings[j], aColumn.strings[j])) {
						return holder.index;
					}
				}
			}
			return -1;
		}
	}

	static class MyHashMap {
		private final MyLinkedList backets[];

		MyHashMap(int capacity) {
			backets = new MyLinkedList[capacity];
		}

		public ColumnHolder get(char[] chars) {
			Key key = new Key(chars);
			int bIndex = key.hashCode % backets.length;
			if (backets[bIndex] == null) {
				return null;
			}
			return backets[bIndex].getByKey(key);
		}

		public void put(char[] chars, ColumnHolder columnHolder) {
			Key key = new Key(chars);
			int bIndex = key.hashCode % backets.length;
			if (backets[bIndex] == null) {
				backets[bIndex] = new MyLinkedList();
			}
			backets[bIndex].add(key, columnHolder);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (MyLinkedList mll : backets) {
				sb.append("[");
				sb.append(mll);
				sb.append("]");
				sb.append("\n");
			}
			return sb.toString();
		}
	}

	static class Key {
		final char ch[];
		final int hashCode;

		Key(char aCh[]) {
			ch = aCh;
			hashCode = calc(ch);
		}

		@Override
		public int hashCode() {
			return hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (super.equals(obj)) {
				return true;
			}

			if (obj instanceof Key) {
				Key other = (Key) obj;
				if (hashCode != other.hashCode || ch.length != other.ch.length) {
					return false;
				}
				for (int i = 0; i < ch.length; ++i) {
					if (ch[i] != other.ch[i]) {
						return false;
					}
				}
			}
			return true;
		}

		private int calc(char[] ch) {
			int hash = 1;
			for (char c : ch) {
				hash *= 31;
				hash += (c - 'a');
				hash %= SIZE;
			}
			return hash;
		}
	}

	static class MyNode {
		Key key;
		ColumnHolder data;
		MyNode next;

		MyNode(Key aKey, ColumnHolder aHolder) {
			key = aKey;
			data = aHolder;
		}
	}

	static class MyLinkedList {
		MyNode head;

		void add(Key key, ColumnHolder aHolder) {
			if (head == null) {
				head = new MyNode(key, aHolder);
				return;
			}
			MyNode cur = head;
			while (cur.next != null) {
				if (cur.key.equals(key)) {
					cur.data = aHolder;
					return;
				}
				cur = cur.next;
			}
			if (cur.key.equals(key)) {
				cur.data = aHolder;
			} else {
				cur.next = new MyNode(key, aHolder);
			}
		}

		ColumnHolder getByKey(Key aKey) {
			MyNode cur = head;
			while (cur != null) {
				Key cKey = cur.key;
				if (cKey.hashCode == aKey.hashCode && cKey.equals(aKey)) {
					return cur.data;
				}
				cur = cur.next;
			}
			return null;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			MyNode cur = head;
			while (cur != null) {
				sb.append("{");
				sb.append("k:");
				sb.append(new String(cur.key.ch));
				sb.append(", ");
				sb.append(cur.key.hashCode);
				sb.append("; i:");
				sb.append(cur.data.index);
				sb.append("}");
				cur = cur.next;
			}
			return sb.toString();
		}
	}
}
