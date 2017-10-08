//328
//n = 2
//0 0
//0 1
//1 1 = 3
//
//0 2
//1 2
//2 2 = 9
//
//0 3
//1 3
//2 3
//3 3 = 18  
//n 4 30  + 12
//
//(n*(n+2))*(n+1) - скільки всього точко
//a1 -> 3
//a2 -> 9 a1 + 6
//a3 -> 18 a2 + 9
//a4 -> 30 a3 + 12
//
//(a1 + an)/2
//an = ( a1 + (a2 - a1)(n - 1)) 
//
//--------------------
//(n*(n+2))
//n = 2; r 12
//S(n + 1) * n

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static final String IDS_INPUT_FILE_NAME = "sample.txt";

	public void run() {

		Scanner scanner = getScanner(false);
		long n = scanner.nextInt();
		long ap = n + 1;
		System.out.println(ap * (ap + 1) / 2 * n);
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