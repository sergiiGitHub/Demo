import java.util.Scanner;

/*
 * https://acmp.ru/index.asp?main=task&id_task=103
 */

public class Main {

	private static final int SIZE = 101;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a[] = createArr(scanner.next());
		int b[] = createArr(scanner.next());
		print(add(a, b));
	}

	private static int[] add(int[] a, int[] b) {
		int res[] = new int[a.length];
		int carry = 0;
		for (int i = 0; i < a.length; ++i) {
			carry = carry + a[i] + b[i];
			res[i] = carry % 10;
			carry /= 10;
		}
		if (carry != 0) {
			throw new Error();
		}
		return res;
	}

	private static void print(int[] a) {
		int i = a.length - 1;
		while (a[i] == 0 && i > 0) {
			--i;
		}
		while (i >= 0) {
			System.out.print(a[i--]);
		}
	}

	private static int[] createArr(String next) {
		int res[] = new int[SIZE];
		for (int i = 0; i < next.length(); ++i) {
			res[next.length() - 1 - i] = next.charAt(i) - '0';
		}
		return res;
	}
}