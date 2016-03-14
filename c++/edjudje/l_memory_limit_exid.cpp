#include <stdio.h>
#include <iostream>
#include <queue>

using namespace std;

int vertex = -1;
int edge = -1;

int **graph;
bool *visited;

void print(){
    for ( int i = 1; i < vertex; ++i ){
        for ( int j = 1; j < vertex; ++j ){
            cout << "[" << i << "][" << j << "] = " << graph[ i ][ j ] << "; ";
        }
        cout << endl;
	}
}

int bfs( int& start, int& end){

	queue< int > queue;
	queue.push(start);
	graph[0][ start ] = 0;

	while (!queue.empty() ){
		int from = queue.front();
		//cout << "bfs :: from " << from << endl;
		visited[ from ] = true;
		queue.pop();
		for (int to = 1; to < vertex; ++to){
            if ( graph[from][to] == 0 ){
                continue;
            }
			if (!visited[ to ] ){
                int val = graph[from][to] + graph[0][ from ];
                if ( graph[0][ to ] > val ){
                    graph[0][ to ] = val;
                }
				queue.push( to );
			}
		}
	}
	return graph[0][ end ];
}

void read(){
    freopen ( "input.txt", "r", stdin);
	cin >> vertex >> edge;
	vertex++;
	graph = new int*[ vertex ];
	visited = new bool[ vertex ];

	int i;
	int maxInt = (1 << 31)-1;
    graph[ 0 ] = new int[ vertex ];
	for ( i = 1; i < vertex; ++i ){
        graph[ i ] = new int[ vertex ];
        graph[ 0 ][ i ] = maxInt;
	}

	int v1, v2, w;
	for ( i = 0; i < edge; ++i ){
        cin >> v1 >> v2 >> w;
		graph[ v1 ][ v2 ] = w;
		graph[ v2 ][ v1 ] = w;
	}
	fclose (stdin);
}

void clearAll(){
	for( int i = 0; i < vertex; ++i ){
        delete [] graph[i];
	}
    delete [] graph;
	delete [] visited;
}

int main(int argc, char* argv[])
{
    read();
    //print();
    int start = 1;
    int endValue = vertex - 1;
	cout << bfs( start, endValue) << endl;

    clearAll();
	return 0;
}

