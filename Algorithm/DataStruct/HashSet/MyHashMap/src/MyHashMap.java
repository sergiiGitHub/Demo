import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyHashMap {
	public static final int BUCKET_SIZE = 29;

	private final List<User>[] buckets;

	public MyHashMap() {
		buckets = new LinkedList[BUCKET_SIZE];
	}

	public int size() {
		int size = 0;
		for (int i = 0; i < BUCKET_SIZE; ++i) {
			final List<User> ll = buckets[i];
			if (ll == null) {
				continue;
			}
			size += ll.size();
		}
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void put(User user) {
		// System.out.println(user);
		List<User> ll = buckets[user.hashCode()];
		if (ll == null) {
			ll = new LinkedList<User>();
			buckets[user.hashCode()] = ll;
		}
		ll.add(user);
	}

	public User get(User user) {
		List<User> ll = buckets[user.hashCode()];
		if (ll == null) {
			return null;
		}
		for (User u : ll) {
			if (u.equals(user)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < BUCKET_SIZE; ++i) {
			final List<User> ll = buckets[i];
			if (ll == null) {
				continue;
			}
			for (User u : ll) {
				sb.append(u);
				sb.append("; ");
			}
			sb.append("\n");
		}
		sb.append("]");

		return sb.toString();
	}

	public void remove(User user) {
		List<User> ll = buckets[user.hashCode()];
		if (ll == null) {
			return;
		}
		Iterator<User> users = ll.iterator();
		while (users.hasNext()) {
			User u = users.next();
			if (u.equals(user)) {
				users.remove();
				break;
			}
		}
	}
}
