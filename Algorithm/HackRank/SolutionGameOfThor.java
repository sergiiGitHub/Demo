import java.util.Scanner;


/*
 * #input 
 * cdcdcdcdeeeef
 * //YES
 * cdefghmnopqrstuvw 
 * //NO
 * aaabbbb
 * //YES
 *
 *https://www.hackerrank.com/challenges/game-of-thrones/
 */

public class Solution{

	private static final boolean IS_DEBUG = true;
	private static final String IDS_INPUT_FILE_NAME = "input.txt";

	private Scanner getScanner() {
		if ( IS_DEBUG ){
			return new Scanner(getClass().getResourceAsStream(IDS_INPUT_FILE_NAME));
		} else {
			return new Scanner(System.in);
		}
	}

	public static void main(String[] args) {
		final int size = 26;
		int array[]  = new int[ size ];
		Solution s = new Solution();
		Scanner sc = s.getScanner(  );
		String inputString = sc.nextLine();
		String ans;
		for ( int i = 0; i < inputString.length(); ++i ){
			int j = inputString.charAt(i) - 'a';
			++array[j];
		}

		int odd = 0;
		for ( int i = 0; i < size; ++i ){
			if ( array[i] % 2 != 0 ){
				++odd;
			}
		}
		if ( odd < 2 ){
			ans = "YES";
		} else {
			ans = "NO";
		}
		System.out.println(ans);
	}
}

