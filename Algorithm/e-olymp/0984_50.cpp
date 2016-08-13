#include <iostream>
#include <stdio.h>

using namespace std;

#define SIZE 11

bool table[SIZE][SIZE];
bool visited[SIZE];
int path[SIZE];

bool cycleVisited[ 2048 ];

int v;
int c;

void dfs( int from, int hashSum, int start, int d ){

    if ( visited[ from ] ) {
        if ( start == from && !cycleVisited[ hashSum ] && d > 2 ){
            ++c;
            cycleVisited[ hashSum ] = true;
            /*
            cout << "s" << start << "; h: " << hashSum << endl;
            for ( int i = 0; i < d; ++i ){
                cout << path[ i ] << " ";
            }
            cout << endl;
            */
        }
        return;
    }
    path[ d ] = from;
    ++d;
    hashSum += ( 2 << from );
    visited[from] = true;
    for ( int to = 0; to < v; ++to){
        if ( table[ from ][ to ] ){
            dfs( to, hashSum, start, d );
        }
    }

    visited[from] = false;
}

int main()
{
    freopen ("input.txt", "r", stdin);
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
        dfs(y, 0, y, 0);
    }

    cout << c;
    return 0;
}
