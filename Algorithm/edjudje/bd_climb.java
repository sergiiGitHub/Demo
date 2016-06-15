
import java.util.Scanner;

public class Solution{
	
	private static final boolean IS_DEBUG = true;
	private static final int NUMBER_OF_VERTICES = 50;
	private static final String IDS_INPUT_FILE_NAME = "input.txt";
	
	private final int table[][];
	private final boolean visted[][];
	
	int w =-1 , h = -1;
	int endX = 0;
	int endY = 0;
	int minMaxHeight = 50;
	
	public Solution() {
		table = new int[ NUMBER_OF_VERTICES ][ NUMBER_OF_VERTICES ];
		visted = new boolean[ NUMBER_OF_VERTICES ][ NUMBER_OF_VERTICES ];
	}
	
	public Scanner getScanner() {
		if ( IS_DEBUG ){
			return new Scanner(getClass().getResourceAsStream(IDS_INPUT_FILE_NAME));
		} else {
			return new Scanner(System.in);
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();		
		System.out.print( solution.getAnswer() );
	}

	public int getAnswer(){
		
		Scanner sc = getScanner();
		
		h = sc.nextInt();
		w = sc.nextInt();

		int startX = 0;
		int startY = 0;
		
		for ( int y = 0; y < h; ++y ){
			for ( int x = 0; x < w; ++x ){
				table[y][x] = sc.nextInt();
				if ( table[y][x] == 2 ){
					startX = x;
					startY = y;
				} else if (table[y][x] == 3 ){
					endX = x;
					endY = y;
				}
			}
		}
		
		int minHeight = 1;
		dfs( startY, startX, minHeight );

		return minMaxHeight;
	}

	int abs( int a ){
		if ( a > 0 ){
			return a;
		}else{
			return -a;
		}
	}
							 // u, d, r, l
	private final int dX [] = { 0, 0, 1, -1 };
	private final int dY [] = { -1, 1, 0, 0 };
	
	private void dfs(int startY, int startX, int aMaxHeight) {
		if ( startX == endX && startY == endY ){
			if ( minMaxHeight > aMaxHeight ){
				minMaxHeight = aMaxHeight;
			}
			System.out.println("aMaxHeight:" + aMaxHeight);
			printVisited();
			System.out.println();

			return;
		}
		
		visted[ startY ][startX] = true;
		for ( int d = 0; d < dX.length; ++d ){
			int newX = startX + dX[d];
			int newY = startY + dY[d];
			if ( newX >= 0 && newX < w && newY >= 0 && newY < h ){
				if ( visted[newY][newX] ){
					continue;
				}
				if ( table[newY][newX] == 0 ){
					if ( d < 2 ){
						climb( newY, newX, d, aMaxHeight );
						continue;
					} else {
						break;
					}
				}

				dfs( newY, newX, aMaxHeight );
			}
		}
		visted[ startY ][startX] = false;
	}
	
	private void climb(int startY, int startX, int direction, int aMaxHeight){
		//System.out.println( "y: " + startY + "; x: " + startX + "; d: " + direction + ";h: " + aMaxHeight );
		if ( visted[ startY ][startX] ){
			return;
		}
		visted[ startY ][startX] = true;
		int newX = startX + dX[direction];
		int newY = startY + dY[direction];
		if ( newX >= 0 && newX < w && newY >= 0 && newY < h ){
			++aMaxHeight;
			if ( table[newY][newX] == 0 ){
				climb(newY, newX, direction, aMaxHeight);
			} else {
				dfs( newY, newX, aMaxHeight);
			}
		}
		visted[ startY ][startX] = false;
	}

	private void printVisited() {
		for ( int y = 0; y < h; ++y ){
			for ( int x = 0; x < w; ++x ){
				System.out.print((visted[y][x] ? 1 : 0) +" ");
			}
			System.out.println();
		}
	}

}
