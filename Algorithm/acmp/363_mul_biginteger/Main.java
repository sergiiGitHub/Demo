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
			carry = a[i] * m + carry + res[i + shift];
			res[i + shift] = carry % 10;
			carry /= 10;
		}
		if (carry != 0) {
			res[i + shift] = carry % 10;
			carry /= 10;
		}
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
		int a[] = new int[st.length()];
		for (int i = a.length - 1; i >= 0; --i) {
			a[a.length - 1 - i] = st.charAt(i) - '0';
		}
		return a;
	}
}
