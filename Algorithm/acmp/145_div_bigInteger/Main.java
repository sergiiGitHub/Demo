import java.util.Scanner;

/*
 * https://acmp.ru/index.asp?main=task&id_task=145
 */

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a[] = createArray(scanner);
		int div[] = new int[a.length];
		long r = divWithRem(a, scanner.nextLong(), div);
		print(div);
	}

	private static long divWithRem(int[] a, long b, int div[]) {
		long rem = 0;
		for (int i = a.length - 1; i >= 0; --i) {
			rem = rem * 10 + a[i];
			div[i] = (int) (rem / b);
			rem %= b;
		}
		return rem;
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

	private static int[] createArray(Scanner scanner) {
		String next = scanner.next();
		int a[] = new int[next.length()];
		for (int i = 0; i < next.length(); ++i) {
			a[next.length() - 1 - i] = next.charAt(i) - '0';
		}
		return a;
	}

}