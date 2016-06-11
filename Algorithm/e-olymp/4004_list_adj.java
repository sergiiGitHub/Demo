
import java.util.Scanner;

public class Solution{
	
	private final static boolean IS_DEBUG = true;
	private final static int NUMBER_OF_VERTICES = 50;
	private static final String IDS_INPUT_FILE_NAME = "input.txt";
	
	private final int graph[][];
	private final int size[];
	private final boolean visted[];
	private int vertexCount = -1;
	
	public Solution() {
		graph = new int[ NUMBER_OF_VERTICES ][ NUMBER_OF_VERTICES ];
		visted = new boolean[ NUMBER_OF_VERTICES ];
		size = new int[ NUMBER_OF_VERTICES ];
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
		fill();
		for ( int v = 0; v < vertexCount; ++v ){
			if ( dfs( v ) ){
				return 1;
			}
		}		
		return 0;
	}
	
	private void fill(){
		final Scanner sc = getScanner();
		
		if (sc.hasNextInt()){
			vertexCount = sc.nextInt();		
		}
		for ( int y = 0; y < vertexCount; ++y ){
			size[y] = 0;
			for ( int x = 0; x < vertexCount; ++x ){
				if ( sc.nextInt() == 0 ){
					continue;
				}
				graph[y][size[y]] = x;
				++size[y];
			}
			visted[y] = false;
		}
	}

	private boolean dfs(int vFrom) {
		for ( int vToIndex = 0; vToIndex < size[vFrom]; ++vToIndex ){
			final int vTo = graph[vFrom][vToIndex]; 
			if ( visted[vTo] ){
				return true;
			}
			visted[ vFrom ] = true;
			if ( dfs( vTo )){
				return true;
			}
			visted[ vFrom ] = false;
		}
		return false;
	}
}
