import java.util.Scanner;

/*
 * https://acmp.ru/index.asp?main=task&id_task=172
 */

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a[] = createArray(scanner);
		System.out.print(rem(a, scanner.nextLong()));
	}

	private static long rem(int[] a, long b) {
		long rem = 0;
		for (int i = a.length - 1; i >= 0; --i) {
			rem = (rem * 10 + a[i]) % b;
		}
		return rem;
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
