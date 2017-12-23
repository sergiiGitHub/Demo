import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static final String IDS_INPUT_FILE_NAME = "sample.txt";
	private static final boolean DEBUG = false;

	public void run() {

		Scanner scanner = getScanner(false);
		long minX = scanner.nextInt();
		long minY = scanner.nextInt();

		long maxX = scanner.nextInt();
		long maxY = scanner.nextInt();

		long wX = scanner.nextInt();
		long wY = scanner.nextInt();
		long wR = scanner.nextInt();

		// normalize
		minX -= wX;
		maxX -= wX;
		minY -= wY;
		maxY -= wY;

		wX = 0;
		wY = 0;

		long x = 0;
		long count = 0;
		for (long y = -wR; y <= wR; ++y) {
			if (minY <= y && y <= maxY) {
				while ((x + 1) * (x + 1) + y * y <= wR * wR) {
					++x;
				}
				while ((y) * (y) + x * x > wR * wR) {
					--x;
				}

				if (!(x < minX || maxX < -x)) {
					count += Math.min(x, maxX) - Math.max(-x, minX) + 1;
				}
			}

		}

		System.out.print(count);
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