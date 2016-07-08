// smth.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

#define MAX_GRID_SIZE 10
#define MAX_NEIGHTBORD 8

int mWidth = -1;
int mHeight = -1;

char NEED_PAINT = '-';
char NOT_PAINTED = '#';

int mGrid[MAX_GRID_SIZE*MAX_GRID_SIZE];
bool mVisited[MAX_GRID_SIZE*MAX_GRID_SIZE];
int graph[MAX_GRID_SIZE*MAX_GRID_SIZE][MAX_NEIGHTBORD + 1];

int getIndex( int height, int width );
void fillGraph( int aHeight, int aWidth, int aIndex );
void printGraph();
int bfs( int &start );
int getRange( int start, int end );

int main(int argc, char* argv[]){

	freopen( "inp.txt", "r", stdin );

	char NEED_PAINT = '-';
	char NOT_PAINTED = '#';

	cin >> mWidth >> mHeight;
	cout << mWidth << " " << mHeight << endl;

	char value;
	for( int height = 0; height < mHeight; height++ ){
		for( int width = 0; width < mWidth; width++ ){
			cin >> value;
			const int index = getIndex( height, width );
			mGrid[ index ] = value;
			if ( value == NEED_PAINT ){
				fillGraph( height, width, index );
				mVisited[index] = false;
			} else {
				graph[index][0] = 0;
				mVisited[index] = true;
			}
		}
	}

	printGraph();

	int shatampSize = 200;
	for( int height = 0; height < mHeight; height++ ){
		for( int width = 0; width < mWidth; width++ ){
			int index = getIndex( height, width );
			if ( mVisited[index] ){ continue; }
			int size = bfs(index);
			cout << "hight = " << height << "; width = " << width;
			cout << "; size = " << size << endl;
			if( size == 0 ){
				size = 1;
			}

			if ( size < shatampSize ){
				shatampSize = size;
			}
		}
	}
	//cout << shatampSize << endl;

	return 0;
}

void fillGraph( int aHeight, int aWidth, int aIndex ){

	//if take to acount upper cell
	//int loacalStartHeight = ( aHeight > 0 ) ? aHeight - 1 : aHeight;

	int loacalStartHeight = ( aHeight == mHeight - 1 ) ? aHeight - 1 : aHeight;
	int loacalEndHeight = ( aHeight == mHeight - 1 ) ? mHeight : aHeight + 2;

	int loacalStartWidth = ( aWidth > 0 && aHeight != mHeight - 1 && aHeight != 0 ) ? aWidth - 1 : aWidth;
	int loacalEndWidth = ( aWidth == mWidth - 1 ) ? mWidth : aWidth + 2;
	int counter = 1;

	for ( int h = loacalStartHeight; h < loacalEndHeight; h++ ){
		for ( int w = loacalStartWidth; w < loacalEndWidth; w++ ){
			if ( w == aWidth && h == aHeight ){
				continue;
			}

			int value = getIndex( h, w );
			graph[ aIndex ][ counter ] = value;
			counter++;
		}
	}
	graph[ aIndex ][0] = counter - 1;
}

int getIndex( int height, int width ){
	return height * mWidth + width;
}

int bfs( int &start ){

	vector< bool > visited( MAX_GRID_SIZE * MAX_GRID_SIZE, false );
	queue< int > queue;

	queue.push( start );
	visited.at( start ) = true;
	const int maxValue = mWidth*mHeight-1;

	while( !queue.empty() ){
		int from = queue.front();
		queue.pop();
		for ( int index = 0; index < graph[from][0]; index++ ){
			int to = graph[from][index+1];
			if ( !visited[to] ){
				if ( mGrid[to] == NOT_PAINTED ){
					visited.clear();
					return getRange( start, from );
				}else if( to == maxValue ){
					visited.clear();
					return getRange( start, to );
				}
				mVisited[to] = true;//added
				visited[to] = true;
				queue.push( to );
			}
		}
	}
	return -1;
}

void printGraph(){
	for( int height = 0; height < mHeight; height++ ){
		for( int width = 0; width < mWidth; width++ ){
			int size = graph[getIndex(height, width)][0];
			cout << size << " ";
			//cout << " {";
			//for ( int index = 0; index < size; index++ ){
			//	cout << graph[getIndex(height, width)][index+1] << " ";
			//}
			//cout << "}" << endl;
		}
		cout << endl;
	}
}

int getRange( int start, int end ){
	cout << "end = " << end << "; start = " << start;
	const int value = max( abs( end % mWidth - start % mWidth), abs( end / mWidth - start / mWidth) );
	cout << "value = " << value << endl;
	return value;
}
