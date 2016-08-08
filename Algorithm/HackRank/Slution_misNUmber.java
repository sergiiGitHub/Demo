import java.util.Scanner;


/*
 * #input 
 3
10 2 5
12 4 4
6 2 2

6
3
5
 *
 *https://www.hackerrank.com/challenges/chocolate-feast
 */

public class Solution{

	private static final boolean IS_DEBUG = true;
	private static final String IDS_INPUT_FILE_NAME = "input.txt";

	public static void main(String[] args) {

		new Solution().solve();
	}

	@SuppressWarnings("resource")
	private void solve() {
		// TODO Auto-generated method stub
		Scanner sc = null;
		if ( IS_DEBUG ){
			sc = new Scanner(getClass().getResourceAsStream(IDS_INPUT_FILE_NAME));
		} else {
			sc = new Scanner(System.in);
		}
		
		final int size = 10000;
        int arr[] = new int[size];
        int in = sc.nextInt();
        int v;
        int min = 1000010;
        int max = 0;
        
        while( in > 0 ){
        	--in;
        	v = sc.nextInt();
        	--arr[ v ];
        	if ( v < min ){
        		min = v;
        	}
        	if ( v > max ){
        		max = v;
        	}
        }
        
        in = sc.nextInt();
        while( in > 0 ){
        	--in;
        	v = sc.nextInt();
        	++arr[ v ];
        	if ( v < min ){
        		min = v;
        	}
        	if ( v > max ){
        		max = v;
        	}
        }
        
        for( int i = min; i < max; ++i ){
        	while ( arr[i] != 0 ){
        		System.out.print( i + " " );
        		if ( arr[i] > 0 ){
        			--arr[i];
        		} else {
        			++arr[i];
        		}
        	}
        }
	}
}

