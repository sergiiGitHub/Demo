#include <iostream>
#include <stdio.h>

using namespace std;

#define SIZE 100

bool table[SIZE][SIZE];
bool visited[SIZE];
int n, i, c;

void dfs( int v ){
    if ( visited[v] ){
        return;
    }

    visited[v] = true;
    ++c;
    for ( int i = 0; i < n; ++i ){
        if ( table[ v ][ i ] ){
            dfs( i );
        }
    }
}

int main()
{
    //freopen ("input.txt", "r", stdin);
    c = 0;
    cin >> n >> i;
    --i;


    for ( int y = 0; y < n; ++y ){
        for ( int x = 0; x < n; ++x ){
            cin >> table[y][x];
        }        
    }

    dfs(i);

    cout << c;
    return 0;
}