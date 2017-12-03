import junit.framework.Assert;

import org.junit.Test;

public class MainTest {

	@Test
	public void test() {
		Solution us = new Solution();
		// map size
		us.init(15);

		us.addBuilding(1, 4, 4, 6, 3, 2, 0);
		Assert.assertFalse(us.isPossibleMove(Solution.Direction.UP,
				Solution.Direction.DOWN, 1, 1));

		Assert.assertTrue(us.isPossibleMove(Solution.Direction.NONE,
				Solution.Direction.UP, 6, 4));

		Assert.assertFalse(us.isPossibleMove(Solution.Direction.LEFT,
				Solution.Direction.DOWN, 6, 3));

		Assert.assertTrue(us.isPossibleMove(Solution.Direction.RIGHT,
				Solution.Direction.DOWN, 6, 3));
		us.addBuilding(2, 11, 2, 3, 8, 0, 3);

		// Assert.assertEquals(11, us.getDistance(1, 2));
	}
}
