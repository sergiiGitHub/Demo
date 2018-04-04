import java.util.Scanner;

/*
 * https://acmp.ru/index.asp?main=task&id_task=144
 */

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a[] = createArray(scanner);
		int b[] = createArray(scanner);
		print(mul(a, b));
	}

	private static int[] createArray(Scanner scanner) {
		String next = scanner.next();
		int a[] = new int[next.length()];
		for (int i = 0; i < next.length(); ++i) {
			a[next.length() - 1 - i] = next.charAt(i) - '0';
		}
		return a;
	}

	private static int[] mul(int[] a, int[] b) {
		int res[] = new int[a.length + b.length];

		for (int i = 0; i < b.length; ++i) {
			mul(a, b[i], i, res);
		}

		return res;
	}

	private static void mul(int[] a, int m, int shift, int[] res) {
		int carry = 0;
		int i = 0;
		for (; i < a.length; ++i) {
			carry = res[i + shift] + carry + a[i] * m;
			res[i + shift] = carry % 10;
			carry /= 10;
		}

		for (; i < res.length && carry != 0; ++i) {
			res[i + shift] += carry % 10;
			carry /= 10;
		}
		if (carry != 0) {
			throw new Error();
		}
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
}