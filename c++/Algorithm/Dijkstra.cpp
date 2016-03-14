/*
 В первой строке задано числа N (2 ≤ N ≤ 105) и M (1 ≤ M ≤ 105) – количество вершин и ребер в графе соответственно.

Далее следует M строк, которые описывают каждое ребро в графе. Каждая строка содержит три целых числа u (1 ≤ u ≤ N), v (1 ≤ u ≤ N) и w (1 ≤ w ≤ 107) – номера двух вершин, которые соединяет ребро и вес этого ребра соответственно.

4 5
1 2 10
1 3 30
2 3 5
2 4 30
3 4 10
------
25

5 7
1 2 3
2 3 4
3 5 4
1 4 4
4 5 5
2 4 2
3 4 2
-----
9


*/

#include <stdio.h>
#include <iostream>
#include <queue>

using namespace std;

int vertex = -1;
int edge = -1;

int **graph;
bool *visited;
int *result;

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
	result[ start ] = 0;

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
                int val = graph[from][to] + result[ from ];
                if ( result[ to ] > val ){
                    result[ to ] = val;
                }
				queue.push( to );
			}
		}
	}
	return result[ end ];
}

void read(){
    //freopen ( "input.txt", "r", stdin);
	cin >> vertex >> edge;
	vertex++;
	graph = new int*[ vertex ];
	result = new int[ vertex ];
	visited = new bool[ vertex ];

	int i;
	int maxInt = (1 << 31)-1;
	for ( i = 0; i < vertex; ++i ){
        graph[ i ] = new int[ vertex ];
        result[ i ] = maxInt;
	}

	int v1, v2, w;
	for ( i = 0; i < vertex; ++i ){
        cin >> v1 >> v2 >> w;
		graph[ v1 ][ v2 ] = w;
		graph[ v2 ][ v1 ] = w;
	}
	//fclose (stdin);
}

void clearAll(){
    for( int i = 0; i < vertex; ++i ){
        delete [] graph[i];
    }
    delete [] graph;
    delete [] visited;
    delete [] result;
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

