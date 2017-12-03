/**
 * split number to simple
 * For example
 * 30 
 * 2*3*5
 * https://acmp.ru/?main=task&id_task=354
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static final String IDS_INPUT_FILE_NAME = "sample.txt";
	private static final boolean DEBUG = false;

	public void run() {

		Scanner scanner = getScanner(DEBUG);
		int n = scanner.nextInt();

		long i = 2;
		while (i * i <= n) {
			if (n % i == 0) {
				System.out.print(i);
				n /= i;
				System.out.print('*');
			} else {
				++i;
			}
		}
		System.out.print(n);

		// System.out.println(max + min);
	}

	private Scanner getScanner(boolean isDebug) {

		if (!isDebug) {
			return new Scanner(System.in);
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(IDS_INPUT_FILE_NAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return scanner;
	}

	public static void main(String[] args) {
		new Main().run();
	}
}
