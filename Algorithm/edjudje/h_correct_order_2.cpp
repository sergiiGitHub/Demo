// DFS.cpp : Defines the entry point for the console application.
//

#include <iostream>
#include <stdio.h>

using namespace std;

int **graph;
int *out;
int vertex = 0;
int edge = 0;

void print(){
    for ( int i = 1; i < vertex; ++i ){
        int sizeInternal = graph[ 0 ][ i ];
        cout << "v = " << i << "( o = "<< out[ i ]  <<" ) : "  ;
        for ( int j = 0; j < sizeInternal; ++j ){
            cout << graph[ i ][ j ] << ", ";
        }
        cout << endl;
    }
}

void read(){
    //freopen ( "input.txt", "r", stdin);

    int v1, v2;
    cin >> vertex >> edge;
    vertex++;
    graph = new int*[ vertex ];
    out = new int[ vertex ];

    for ( int i = 0; i < vertex; ++i ){
        graph[ i ] = new int[ vertex ];
        graph[ 0 ][ i ] = 0;
        out[i] = -1;
    }

    for ( int i = 0; i < edge; ++i ){
        cin >> v1 >> v2;
        graph[ v1 ][ graph[ 0 ][ v1 ] ] = v2;
        ++graph[ 0 ][ v1 ];
    }
    //print();
    //fclose (stdin);
}

void clearLocal(){
    for ( int i = 0; i < edge; ++i ){
        delete [] graph[i];
    }
    delete [] graph;
    delete [] out;
}

void dfs(const int &vertex, int &count ){
    if ( out[ vertex ] != -1 ){
        return;
    }

    ++count;
    for ( int j = 0; j < graph[ 0 ][ vertex ]; ++j){
        dfs( graph[vertex][j], count );
    }
    out[vertex]= ++count;
}

void justMerge(int left, int mid, int right ){
    int k = 0, i = left, j = mid + 1;

    while (i <= mid && j <= right){
        if ( out[ i ] < out[ j ] ){
            graph[0][k] = graph[1][i];
            graph[2][k] = out[ i ];
            ++i;
        } else {
            graph[0][k] = graph[1][j];
            graph[2][k] = out[ j ];
            ++j;
        }
        ++k;
    }
    while (i <= mid){
        graph[0][k] = graph[1][i];
        graph[2][k] = out[ i ];
        ++i;
        ++k;
    }
    for (i = 0; i < k; ++i){
        graph[1][left + i] = graph[0][i];
        out[ left + i ] = graph[2][i];
    }
}

void mergeSort( int left, int right ){
    if ( right - left < 1 ){
        return;
    }
    int mid = (left + right) / 2;
    mergeSort( left, mid );
    mergeSort( mid + 1, right );
    justMerge( left, mid, right );
}

void solve(){

    int count = 0;
    for ( int vertex = 1; vertex <= edge; ++vertex){
        dfs( vertex, count );
    }
    //print();
    for ( int i = 0; i < vertex; ++i ){
        graph[ 0 ][ i ] = i;
        graph[ 1 ][ i ] = i;
    }

    mergeSort( 1, vertex - 1 );

    for ( int i = vertex - 1; i >= 1; --i ){
        cout << graph[ 1 ][ i ] << " ";
    }
}

int main()
{
    read();

    solve();

    clearLocal();
    return 0;
}

