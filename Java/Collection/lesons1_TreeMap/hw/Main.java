
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static final String IDS_INPUT_FILE_NAME = "sample.txt";
	public void run(){
		Scanner scanner = getScanner(true);
				
		if (scanner.hasNextLine()){
			String str = scanner.next();
			System.out.println(str);
		}
	}
	
	private Scanner getScanner(boolean isDebug) {
		
		if (!isDebug){
			return new Scanner(System.in);
		}
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(IDS_INPUT_FILE_NAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
		return scanner;
	}

	public static void main(String[] args) {
		new Main().run();
	}
}
