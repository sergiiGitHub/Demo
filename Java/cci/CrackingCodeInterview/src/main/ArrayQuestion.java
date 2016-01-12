package main;

public class ArrayQuestion {	

	public void isShifted() {
		String str1 = "waterbottle";
		String str2 = "erbo	ttlewat";
		
		String check = str1 + str1;
				
		System.out.println( str2 + "is shifted of " + str1 + " " + check.contains(str2) );
	}

	public void changeEntireRowandColumn() {
		
		int matrix[][] = {
				{ 2, 3, 4, 5 },
				{ 2, 3, 0, 5 },
				{ 2, 3, 4, 5 }
		};
		//my 
		// minus - memory consumption n*m
		// minus - complexity consumption n*m( n + m );
		//boolean check[][] = new boolean[ matrix.length ][ matrix[0].length ];
		//check
//		for( int r = 0; r < matrix.length; ++r ){
//			for ( int c = 0; c < matrix[r].length; c++ ){
//				if ( matrix[r][c] == 0 ){
//					check[r][c] = true; 
//				}
//			}
//		}
//		for( int r = 0; r < matrix.length; ++r ){
//			for ( int c = 0; c < matrix[r].length; c++ ){
//				if ( check[r][c] ){
//					for( int cr = 0; cr < matrix.length; cr++ ){
//						matrix[cr][c] = 0;
//					}
//					for( int cc = 0; cc < matrix[r].length; cc++ ){
//						matrix[r][cc] = 0;
//					}
//				}
//			}
//		}
		boolean row[] = new boolean[ matrix.length ];
		boolean column[] = new boolean[ matrix[0].length ];
		for( int r = 0; r < matrix.length; ++r ){
			for ( int c = 0; c < matrix[r].length; c++ ){
				if ( matrix[r][c] == 0 ){
					row[r] = true;
					column[c] = true; 
				}
			}
		}
		
		for( int r = 0; r < matrix.length; ++r ){
			for ( int c = 0; c < matrix[r].length; c++ ){
				if ( row[r] | column[c] ){
					matrix[r][c] = 0;
				}
			}
		}
		//display
		for( int r = 0; r < matrix.length; ++r ){
			for ( int c = 0; c < matrix[r].length; c++ ){
				System.out.print(matrix[r][c]);
				System.out.print(", ");
			}
			System.out.println();
		}
	}

	public void removeDublicate() {
		
		char str[] = { 'a', 'a', 'b', 'c', '\0' };
		System.out.println( "Before = ");
		for( char ch : str ){
			System.out.print( ch );
		}
		
		int len = str.length;
		if ( len < 2 ){
			return;
		}
		int tail = 1;
		for ( int i = 1; i < len; i++ ){
			int j;
			for ( j = 0; j < tail; j++ ){
				if( str[i] == str[j] ){
					break;
				}
			}
			if ( j == tail ){
				str[tail] = str[ i ];
				++tail;
			}
		}
		str[tail] = 0;
		System.out.println( "After = " + str );
		for( char ch : str ){
			System.out.print( ch );
		}
	}

	public void isUniaque() {
		String str = "absa";
		
		boolean[] counter = new boolean[65535];
		boolean result = true;
		for ( int i = 0; i < str.length(); i++ ){
			if ( counter[ str.charAt(i) ] ){
				result = false;
				break;
			}
			counter[ str.charAt(i) ] = true;
		}
		System.out.println( str + " is Unique = " + result);
	}	
}
