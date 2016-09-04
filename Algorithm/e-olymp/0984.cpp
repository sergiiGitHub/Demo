#include <iostream>
#include <stdio.h>

using namespace std;

#define SIZE 11

bool table[SIZE][SIZE];
bool visited[SIZE];

int v;
int c;

void dfs( int from, int parent, int root ){

    if ( visited[ from ] ) {
        return;
    }
    visited[from] = true;
    for ( int to = 0; to < v; ++to){
        if ( table[ from ][ to ] && to != parent ){
            if ( to == root ){
                c++;
                continue;
            }
            dfs( to, from, root );
        }
    }

    visited[from] = false;
}

void go( int v ){
    dfs( v, -1 , v);
    visited[ v ] = true;
}

int main()
{
    //freopen ("input.txt", "r", stdin);
    int r;
    cin >> v >> r;
    int v1, v2;

    for( int y = 0; y < v; y++ ){
        for( int x = 0; x < v; x++ ){
            table[y][x] = false;
        }
        visited[y] = false;
    }

    for ( int y = 0; y < r; ++y ){
        cin >> v1 >> v2;
        --v1;
        --v2;
        table[v1][v2] = true;
        table[v2][v1] = true;
    }

    c = 0;
    for ( int y = 0; y < r; ++y ){
        go( y );
    }

    cout << c / 2 << endl;
    return 0;
}