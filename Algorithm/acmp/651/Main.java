import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static final String IDS_INPUT_FILE_NAME = "sample.txt";
	private static final boolean DEBUG = false;

	public void run() {

		Scanner scanner = getScanner(DEBUG);
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		// remove common divider
		int gdcNm = gcd(n, m);
		int dn = n / gdcNm;
		int dm = m / gdcNm;

		// calculate single number
		// 2 * 2 * 3 * 3 * 17
		// + + + + +
		int d = calc(dn);
		d += calc(dm);

		System.out.println(d);

	}

	private int calc(int dn) {
		int res = 0;
		int d = 2;
		while (d * d <= dn) {
			if (dn % d == 0) {
				++res;
				dn /= d;
			} else {
				++d;
			}
		}
		if (1 < dn) {
			++res;
		}
		return res;
	}

	int gcd(int a, int b) {
		while (b != 0) {
			int t = a % b;
			a = b;
			b = t;
		}
		return a;
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