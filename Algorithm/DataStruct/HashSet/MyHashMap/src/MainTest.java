import org.junit.Assert;
import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		final String strs[] = { "absjsdhshs", "absjsdhdshs", "absadfaahs",
				"aakdssjjja", "aaaaaabbbab", "absjsdhshs", "absjsdhshs",
				"skskskskksk", "akakakakka", "hashdhsfhd", "aaabbbccccc",
				"afaafaaffa" };
		final int size = 10;
		MyHashMap map = new MyHashMap();
		for (String str : strs) {
			map.put(new User(str));
		}
		System.out.print(map);
		// size
		Assert.assertEquals("Size", map.size(), strs.length);
		for (String str : strs) {
			User user = new User(str);
			Assert.assertEquals("Not found", user, map.get(user));
		}

		// add remove
		User user = new User("aaa");
		map.put(user);
		Assert.assertEquals("Not found", user, map.get(user));
		map.remove(user);
		Assert.assertNull("Found", map.get(user));

		// size
		Assert.assertEquals("Size", map.size(), strs.length);

	}
}
