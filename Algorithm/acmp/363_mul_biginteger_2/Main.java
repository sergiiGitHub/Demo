import java.util.Scanner;

/*
 * https://acmp.ru/index.asp?main=task&id_task=363
 */

public class Main {

	static final int SIZE = 674;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int a[] = converToArray(scanner), b[] = converToArray(scanner);

		print(mul(a, b));
	}

	private static int[] mul(int[] a, int[] b) {
		int lenA = a.length;
		int lenB = b.length;
		int res[] = new int[lenA + lenB];

		for (int ib = 0; ib < lenB; ++ib) {
			int carry = 0;
			for (int ia = 0; ia < lenA; ++ia) {
				carry = a[ia] * b[ib] + carry + res[ia + ib];
				res[ia + ib] = carry % 10;
				carry /= 10;
			}
			if (carry != 0) {
				res[ib + lenA] = carry % 10;
				carry /= 10;
			}
			if (carry != 0) {
				throw new Error();
			}
		}
		return res;
	}

	private static void print(int a[]) {
		int i = len(a);

		while (i >= 0) {
			System.out.print(a[i]);
			--i;
		}
	}

	private static int len(int[] a) {
		int i = a.length - 1;
		while (i > 0 && a[i] == 0) {
			--i;
		}
		return i;
	}

	private static int[] converToArray(Scanner s) {
		String st = s.next();
		int a[] = new int[st.length()];
		for (int i = a.length - 1; i >= 0; --i) {
			a[a.length - 1 - i] = st.charAt(i) - '0';
		}
		return a;
	}
}
