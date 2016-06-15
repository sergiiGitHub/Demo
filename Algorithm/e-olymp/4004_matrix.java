
import java.util.Scanner;

public class Solution{
	
	private final static boolean IS_DEBUG = true;
	private final static int NUMBER_OF_VERTICES = 50;
	private static final String IDS_INPUT_FILE_NAME = "input.txt";
	
	private boolean graph[][];
	private boolean visted[];
	
	int vertexCount = -1;
	
	public Solution() {
		graph = new boolean[ NUMBER_OF_VERTICES ][ NUMBER_OF_VERTICES ];
		visted = new boolean[ NUMBER_OF_VERTICES ];
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
		
		if (sc.hasNextInt()){
			vertexCount = sc.nextInt();		
		}
		
		for ( int y = 0; y < vertexCount; ++y ){
			for ( int x = 0; x < vertexCount; ++x ){
				graph[y][x] = sc.nextInt() == 1;
			}
			visted[y] = false;
		}
		
		for ( int v = 0; v < vertexCount; ++v ){
			if ( dfs( v ) ){
				return 1;
			}
		}
		
		return 0;
	}

	private boolean dfs(int vFrom) {
		visted[ vFrom ] = true;
		for ( int vTo = 0; vTo < vertexCount; ++vTo ){
			if ( graph[ vFrom ][ vTo ] ){
				if ( visted[vTo] ){
					return true;
				}

				if ( dfs( vTo )){
					return true;
				}
			}
		}
		visted[ vFrom ] = false;
		return false;
	}

}
