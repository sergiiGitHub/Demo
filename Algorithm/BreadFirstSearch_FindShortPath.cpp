// smth.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int bfs( vector < vector < int > > &aGraph, int &start, int &end );

int main(int argc, char* argv[]){

	freopen( "inp.txt", "r", stdin );
	int vertex;
	int edge;
	cin >> vertex >> edge;
	vector< vector < int > > graph( vertex + 1 );
	for ( int index = 0; index < edge; index++ ){
		int value1, value2;
		cin >> value1 >> value2;
		graph.at(value1).push_back( value2 );
		graph.at(value2).push_back( value1 );
	}

	int start = 1;
	int end = 5;
	bfs( graph, start, end );

	return 0;
}

int bfs( vector < vector < int > > &aGraph, int &start, int &end ){

	vector< bool > visited( aGraph.size(), false );
	queue< int > queue;
	vector< int > races ( aGraph.size(), -1 );

	queue.push( start );
	visited.at( start ) = true;

	bool isForseFinish = false;
	while( !queue.empty() && !isForseFinish ){
		int from = queue.front();
		queue.pop();
		for ( int index = 0; index < aGraph.at(from).size(); index++ ){
			int to = aGraph.at(from).at(index);
			if ( !visited[to] ){
				visited[to] = true;
				races[to] = from;
				queue.push( to );
				if ( to == end ){
					isForseFinish = true;
					break;
				}
			}
		}
	}

	int pointer = end;
	int counter(-1);
	while( races.at( pointer ) != -1 ){
		pointer = races.at( pointer );
		cout << pointer << " ";
		counter++;
	}
	cout << endl;

	return counter;
}
