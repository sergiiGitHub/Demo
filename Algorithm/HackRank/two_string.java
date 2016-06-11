import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {		
	public static void main(String[] args) {
		Solution solution = new Solution();
		solution.solve();
	}
}

class Solution{
	
	private final static boolean IS_DEBUG = false;

	
	private Scanner getScanner() {
		Scanner sc = null;
		if ( IS_DEBUG ){
			try {
				sc = new Scanner(new File( "/home/sergii/_project/input.txt" ));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
		} else {
			sc = new Scanner(System.in);
		}
		return sc;
	}
	
	public void solve() {
		Scanner sc = getScanner();
		int numberTestCase = Integer.parseInt( sc.nextLine() );
		while ( numberTestCase > 0 ){
	        System.out.println(getAnswer( sc ));
	        --numberTestCase;
		}
        
        sc.close();
	}

	private String getAnswer(Scanner sc) {
		boolean isPresent[] = new boolean[26];
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		for ( int i = 0; i < str1.length(); ++i ){
			isPresent[ str1.charAt(i) - 'a' ] = true;
		}
		
		for ( int i = 0; i < str2.length(); ++i ){
			if ( isPresent[ str2.charAt(i) - 'a'] ){
				return "YES";
			}
		}
		
		return "NO";
	}
}
