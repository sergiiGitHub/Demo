// DFS.cpp : Defines the entry point for the console application.
//

#include <iostream>
#include <stdio.h>

#define MAX 100001
using namespace std;

struct Vertex{
    int v;
    int in;
    int out;
    int childSize;
};

Vertex *vertexes;
int **graph;
int vertex = 0;
int edge = 0;

void print(){
    for ( int i = 1; i <= edge; ++i ){
        Vertex vertex = vertexes[i];
        cout << "v = " << i << "( in= "<< vertex.in << ", out= " << vertex.out << "); c = ";
        for ( int j = 0; j < vertex.childSize; ++j ){
            cout << vertexes[ graph[ i ][ j ] ].v << ", ";
            //cout << graph[ i ][ j ] << ", ";
        }
        cout << endl;
    }
}

void read(){
    int v1, v2;
    cin >> vertex >> edge;
    vertex++;
    graph = new int*[ vertex ];
    vertexes = new Vertex[ vertex ];
    
    for ( int i = 0; i <= edge; ++i ){
        graph[ i ] = new int[ vertex ];
        vertexes[i].v = i;
        vertexes[i].in = -1;
        vertexes[i].out = -1;
        vertexes[i].childSize = 0;
    }

    for ( int i = 0; i < edge; ++i ){
        cin >> v1 >> v2;
        graph[ v1 ][ ( vertexes[ v1 ].childSize ) ] = v2;
        vertexes[ v1 ].childSize++;
    }
    //print();
}

void clear(){
    for ( int i = 0; i < edge; ++i ){
        delete [] graph[i];
    }
    delete [] graph;
    delete [] vertexes;
}

void dfs(const int &vertex, int &count ){
    if (  vertexes[vertex].in != -1 ){
        return;
    }

    vertexes[vertex].in = ++count;
    for ( int j = 0; j < vertexes[vertex].childSize; ++j){
        dfs( graph[vertex][j], count );
    }
    vertexes[vertex].out = ++count;
}

void mergeSort( int left, int mid, int rigt ){

    //mergeSort(  );
}

void solve(){   
    
    int count = 0;
    for ( int vertex = 1; vertex <= edge; ++vertex){
        dfs( vertex, count );
    }

    //sort
    mergeSort( 1, vertex/2, vertex );
    //cout

    print();
}

int main()
{
    std::ios_base::sync_with_stdio(0);
    freopen ( "../input.txt", "r", stdin);
    read( );

    solve();

    fclose (stdin);
    clear();
    return 0;
}