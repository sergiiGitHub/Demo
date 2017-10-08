import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * https://acmp.ru/asp/do/index.asp?main=task&id_course=1&id_section=1&id_topic=27&id_problem=157
 * [1..109]
 * 1+v*t
 * 
 * (1+v*t)%109 -> [0..108]
 * 
 * forward 
 * v*t%109 + 1 -> [1..109]
 * backward
 * (v * t % 109 + 109) % 109 + 1 -> [1..109]
 */
public class Main {
	private static final String IDS_INPUT_FILE_NAME = "sample.txt";

	public void run() {
		Scanner scanner = getScanner(false);
		int v = scanner.nextInt();
		int t = scanner.nextInt();
		if (0 < v) {
			System.out.println(v * t % 109 + 1);
		} else {
			System.out.println((v * t % 109 + 109) % 109 + 1);
		}

	}

	int convert(char ch) {
		return ch - '0';
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
