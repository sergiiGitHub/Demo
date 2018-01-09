import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

import org.junit.Assert;

public class MyScanner2 {
	private final StreamTokenizer st;

	public MyScanner2(InputStream is) {
		// st = new StreamTokenizer(new BufferedReader(new
		// InputStreamReader(is)));
		st = new StreamTokenizer(new InputStreamReader(new BufferedInputStream(
				is)));
	}

	// Disadvantage
	// always read double(9e15 (2^53)) (could not read long 9e18(2^63))
	public int nextInt() {
		try {
			st.nextToken();
			Assert.assertTrue(st.ttype == StreamTokenizer.TT_NUMBER);
			int r = (int) st.nval;
			Assert.assertTrue(r == st.nval);
			return r;
		} catch (IOException e) {
			throw new Error();
		}
	}
}
