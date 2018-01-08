import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

import org.junit.Assert;

public class MyScanner {
	private final StreamTokenizer st;

	public MyScanner(InputStream is) {
		// st = new StreamTokenizer(new BufferedReader(new
		// InputStreamReader(is)));
		st = new StreamTokenizer(new InputStreamReader(new BufferedInputStream(
				is)));
		// remove all build in function
		st.resetSyntax();
		// set space (tab, new line)
		st.whitespaceChars(0, 32);
		// read word
		st.wordChars(33, 255);
	}

	public String next() {
		try {
			st.nextToken();
			Assert.assertTrue(st.ttype == StreamTokenizer.TT_WORD);
			return st.sval;
		} catch (IOException e) {
			throw new Error();
		}
	}
}
