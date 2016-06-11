
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution{
	
	private final static boolean IS_DEBUG = true;
	private final static int NUMBER_OF_VERTICES = 50;
	
	private boolean graph[][];
	private boolean visted[];
//	private int parent[];
	
	int vertexCount = -1;
	
	public Solution() {
		graph = new boolean[ NUMBER_OF_VERTICES ][ NUMBER_OF_VERTICES ];
//		parent = new int[ NUMBER_OF_VERTICES ];
		visted = new boolean[ NUMBER_OF_VERTICES ];
	}
	
	public static Scanner getScanner() {
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
				graph[y][x] = sc.nextInt() == 0 ? false : true ;
			}
			visted[y] = false;
//			parent[y] = -1;
		}
		
		//start dfs
		for ( int v = 0; v < vertexCount; ++v ){
			if ( dfs( v ) ){
				return 1;
			}
		}
		
		return 0;
	}

	private boolean dfs(int vFrom) {
		for ( int vTo = 0; vTo < vertexCount; ++vTo ){
			if ( graph[ vFrom ][ vTo ] ){
				if ( visted[vTo] ){
					return true;
				}
				visted[ vFrom ] = true;
				if ( dfs( vTo )){
					return true;
				}
				visted[ vFrom ] = false;
			}
		}
		return false;
	}

}
