import java.io.IOException;
import java.io.InputStream;

// Disadvantage
// can't read double
// large text

// Advantage
// mega fast
// good for int and long

// 2147483648 == 2^32
public class MyScanner3 {
	private final InputStream is;
	byte buffer[] = new byte[1 << 16];
	int size = 0;
	int pos = 0;

	public MyScanner3(InputStream is) {
		this.is = is;
	}

	public static class MyAssert {
		public static void check(boolean v) {
			if (!v) {
				throw new Error();
			}
		}
	}

	// int java soglashenie
	int nextChar() {
		if (pos >= size) {
			try {
				size = is.read(buffer);
			} catch (IOException e) {
				throw new Error();
			}
			pos = 0;
			if (size == -1) {
				return -1;
			}
		}
		MyAssert.check(pos < size);
		// for kirilic letter for example я has -1 value
		int res = buffer[pos] & 0xFF;
		++pos;
		return res;
	}

	public int nextInt() {
		int c = nextChar();
		while (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
			c = nextChar();
		}
		int sign = 1;
		if (c == '-') {
			sign = -1;
			c = nextChar();
		}
		MyAssert.check('0' <= c && c <= '9');
		int n = c - '0';
		c = nextChar();
		while ('0' <= c && c <= '9') {
			int d = c - '0';

			// @formatter:off
			MyAssert.check(sign == 1 && (n < Integer.MAX_VALUE / 10 || n == Integer.MAX_VALUE / 10 && d <= Integer.MAX_VALUE % 10)
					|| sign == -1 && n >= 0 
					&& (n < -(Integer.MIN_VALUE / 10) || n == -(Integer.MIN_VALUE / 10) && d <= -(Integer.MIN_VALUE % 10))
					);
			// @formatter:on
			n = n * 10 + d;
			c = nextChar();
		}
		n *= sign;
		return n;
	}
}
