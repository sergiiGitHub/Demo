import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyHashMap {
	private static final int BUCKET_SIZE = 29;

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
		final int index = user.hashCode() % BUCKET_SIZE;
		List<User> ll = buckets[index];
		if (ll == null) {
			ll = new LinkedList<User>();
			buckets[index] = ll;
		}
		ll.add(user);
	}

	public User get(User user) {
		final int index = user.hashCode() % BUCKET_SIZE;
		List<User> ll = buckets[index];
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
		final int index = user.hashCode() % BUCKET_SIZE;
		final List<User> ll = buckets[index];
		if (ll == null) {
			return;
		}
		final Iterator<User> users = ll.iterator();
		while (users.hasNext()) {
			final User u = users.next();
			if (u.equals(user)) {
				users.remove();
				break;
			}
		}
	}
}
