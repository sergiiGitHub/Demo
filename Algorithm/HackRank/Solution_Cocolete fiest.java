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
		Scanner in = null;
		if ( IS_DEBUG ){
			in = new Scanner(getClass().getResourceAsStream(IDS_INPUT_FILE_NAME));
		} else {
			in = new Scanner(System.in);
		}
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int c = in.nextInt();
            int m = in.nextInt();
            
            int ans = n / c;
            n = ans;
            int remain = 0;
            while ( n >= m ){
            	int cur = n / m;
            	remain += n % m;
            	ans += cur;
            	n = cur + remain;
            	remain = 0;
            	//System.out.println("#"+ a0 + " " + ans);
            }
            
            System.out.println(ans);
        }
	}
}

