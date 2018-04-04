import java.util.Scanner;

/*
 * https://acmp.ru/index.asp?main=task&id_task=18
 */

public class Main {

	static final int SIZE = 2569;

	public static void main(String[] args) {

		int n = new Scanner(System.in).nextInt();
		int fact[] = new int[SIZE];
		fact[0] = 1;

		for (int i = 2; i <= n; ++i) {
			mul(fact, i);
		}

		int i = SIZE - 1;
		while (i > 0 && fact[i] == 0)
			--i;
		while (i >= 0) {
			System.out.print(fact[i]);
			--i;
		}

	}

	private static void mul(int[] fact, int m) {
		int i = 0, carry = 0;
		while (i < SIZE) {
			carry = fact[i] * m + carry;
			fact[i] = carry % 10;
			carry /= 10;
			++i;
		}
	}
}