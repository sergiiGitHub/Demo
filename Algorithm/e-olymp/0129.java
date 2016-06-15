
import java.util.Scanner;

public class Solution{
	
	private final static boolean IS_DEBUG = true;
	private static final String IDS_INPUT_FILE_NAME = "input.txt";
	
	public Scanner getScanner() {
		if ( IS_DEBUG ){
			return new Scanner(getClass().getResourceAsStream(IDS_INPUT_FILE_NAME));
		} else {
			return new Scanner(System.in);
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();		
		solution.solve();
	}

	public void solve(){
		final Scanner sc = getScanner();

		while (sc.hasNextInt()){
			int r = sc.nextInt();
			System.out.println( square(r) );
		}
	}
	
	int square( int r ){
		int a = 0;
		if ( r < 2 ){
			return 0;
		}

		int xInrement = 1;
		for ( int y = r; y > 0; --y ){
			for ( int x = xInrement; x <= r; ++x ){
				if ( r*r >= x*x + y * y ){
					int c = ( y * x );
					int p = ( y * (x - 1) );
					a += c - p;
					++xInrement;
				} else {
					break;
				}
			}
		}
		return a*4;
	}
}
