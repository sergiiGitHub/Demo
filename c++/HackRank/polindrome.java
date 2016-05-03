
/**
https://www.hackerrank.com/challenges/palindrome-index
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {		
	public static void main(String[] args) {
		Solution solution = new Solution();
		solution.solve();
	}
}

class Solution{
	
	private final static boolean isDebug = false;
	
	private Scanner getScanner() {
		Scanner sc = null;
		if ( isDebug ){
			try {
				sc = new Scanner(new File( "/home/sergii/_project/read.txt" ));
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
			int index = getAnswer( sc );
	        System.out.println(index);
	        --numberTestCase;
		}
        
        sc.close();
	}

	private int getAnswer(Scanner sc) {
		String line = sc.nextLine();
		int size = line.length()/2;
		int coefLeft = 0;
		int coefRigth = 0;
		int indexLeft = -1, indexRight = -1;
		for ( int i = 0; i < size; ++i ){
			int iRight = line.length() - 1 - i;
			if ( line.charAt(i) != line.charAt(iRight - coefRigth ) ){
				if ( line.charAt(i) == line.charAt(iRight - coefRigth - 1 ) ){
					indexRight = iRight - coefRigth;
					++coefRigth;
				}
			}
			
			int iLeft = i + coefLeft;
			if ( line.charAt(iLeft) != line.charAt(iRight) ){
				if ( line.charAt(iLeft+1) == line.charAt(iRight) ){
					indexLeft = iLeft;
					++coefLeft;
				}
			}
		}
		if ( coefLeft == 1 ){
			return indexLeft;
		} else if ( coefRigth == 1 ){
			return indexRight;
		}
		
		return -1;
	}
}
