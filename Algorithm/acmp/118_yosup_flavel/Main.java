import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static final String IDS_INPUT_FILE_NAME = "sample.txt";

	public void run() {

		Scanner scanner = getScanner(false);

		int n = scanner.nextInt();
		int k = scanner.nextInt();
		/**
		 * f(n, k) = could discribe by f(n-1, k) f(1, k) = 0 f(n, k) = (k +
		 * f(n-1,k))%n
		 */

		int pos = 0;
		for (int i = 2; i <= n; ++i) {
			pos = (k + pos) % i;
		}

		System.out.println(pos + 1);
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
