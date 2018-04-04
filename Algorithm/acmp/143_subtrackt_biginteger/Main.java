import java.util.Scanner;

/*
 * https://acmp.ru/index.asp?main=task&id_task=18
 */

public class Main {

	static final int SIZE = 1001;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int a[] = converToArray(s);
		int b[] = converToArray(s);
		int cmp = compare(a, b);
		if (cmp > 0) {
			print(subtract(a, b));
		} else if (cmp < 0) {
			System.out.print('-');
			print(subtract(b, a));
		} else {
			System.out.print('0');
		}
	}

	private static int[] subtract(int[] big, int[] small) {
		int res[] = new int[SIZE];
		int carry = 0;
		for (int i = 0; i < SIZE; ++i) {
			carry = carry + big[i] - small[i] + 10;
			res[i] = carry % 10;
			carry /= 10;
			--carry;
		}

		return res;
	}

	private static int compare(int[] a, int[] b) {

		int diff = 0;
		for (int i = a.length - 1; diff == 0 && i >= 0; --i) {
			diff = a[i] - b[i];
		}
		return diff;
	}

	private static void print(int a[]) {
		int i = a.length - 1;
		while (i > 0 && a[i] == 0) {
			--i;
		}
		while (i >= 0) {
			System.out.print(a[i]);
			--i;
		}
	}

	private static int[] converToArray(Scanner s) {
		String st = s.next();
		int a[] = new int[SIZE];
		for (int i = st.length() - 1; i >= 0; --i) {
			a[st.length() - 1 - i] = st.charAt(i) - '0';
		}
		return a;
	}
}