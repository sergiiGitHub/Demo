import java.util.Scanner;

/*
 * https://acmp.ru/index.asp?main=task&id_task=363
 */

public class Main {

	static final int SIZE = 1251;
	static final int BASE = 10_000;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int a[] = readToBigNum(scanner), b[] = readToBigNum(scanner);

		print(mul(a, b));
		// print(a);
	}

	private static int[] mul(int[] a, int[] b) {
		int lenA = getLen(a);
		int lenB = getLen(b);
		int res[] = new int[SIZE];

		for (int ia = 0; ia < lenA; ++ia) {
			int carry = 0;
			for (int ib = 0; ib < lenB; ++ib) {
				carry += res[ia + ib] + a[ia] * b[ib];
				res[ia + ib] = carry % BASE;
				carry /= BASE;
			}
			if (carry > 0) {
				res[ia + lenB] = carry;
			}
		}
		return res;
	}

	private static void print(int a[]) {
		int len = getLen(a);

		System.out.print(a[len - 1]);
		for (int i = len - 2; i >= 0; --i) {
			for (int pow = BASE / 10; pow >= 1; pow /= 10) {
				System.out.print(a[i] / pow % 10);
			}
		}

	}

	private static int getLen(int[] a) {
		int i = a.length;
		while (i > 1 && a[i - 1] == 0) {
			--i;
		}
		return i;
	}

	private static int[] readToBigNum(Scanner s) {
		String st = s.next();
		int a[] = new int[SIZE];
		int ai = 0;
		int dp = 1;
		for (int i = st.length() - 1; i >= 0; --i) {
			a[ai] += (st.charAt(i) - '0') * dp;
			dp *= 10;
			if (dp == BASE) {
				dp = 1;
				++ai;
			}
		}
		return a;
	}
}