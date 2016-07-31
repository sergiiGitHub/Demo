


/*
https://www.hackerrank.com/challenges/sherlock-and-array

 *input 
 *2
 *3
 *1 2 3
 *4
 *1 2 3 3
*/

import java.util.Scanner;

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
		long arraySum[]  = new long[ 100000 ];
		Solution s = new Solution();
		Scanner sc = s.getScanner(  );
		int test = sc.nextInt();
		label:
		while( test > 0 ){
			//System.out.println(test);
			--test;		

			int length = sc.nextInt();
			for ( int i = 0; i < length; ++i ){
				if ( i == 0 ){
					arraySum[i] = sc.nextLong(); 
				} else {
					arraySum[i] = arraySum[i-1] + sc.nextLong();
				}
			}
			for ( int i = 1; i < length; ++i ){
				if( arraySum[i - 1] == arraySum[length-1] - arraySum[i] ){
					System.out.println("YES");
					continue label;
				} 
			}
			System.out.println("NO");
		}
	}
}

