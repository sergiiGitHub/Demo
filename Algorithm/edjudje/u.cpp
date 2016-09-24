#include <iostream>
#include <stdio.h>

using namespace std;

#define SIZE 100
#define pathSize 4

int table[ SIZE ][ SIZE ];
int n;

int inrement;
int tableIncrement[ SIZE ][ SIZE ];
int incrementCount[ SIZE ];


int pathX[ pathSize ] = { 0, 0,  1, -1 };
int pathY[ pathSize ] = { 1, -1, 0, 0 };

int dfs( int y, int x, int& count, int digit ){
    if ( tableIncrement[ y ][ x ] != -1 || digit != table[y][x] ){
        return count;
    }
    ++count;
    tableIncrement[ y ][ x ] = inrement;
    for ( int p = 0; p < pathSize; ++p ){
        int newX = x + pathX[p];
        if ( 0 <= newX && newX < n ){
            int newY = y + pathY[p];
            if ( 0 <= newY && newY < n ){
                dfs( newY, newX, count, digit );
            }
        }
    }
    return count;
}

int solve(){

    for ( int y = 0; y < n; ++y ){
        for ( int x = 0; x < n; ++x ){
            if ( table[y][x] != 0 && tableIncrement[y][x] == -1 ){
                int count = 0;
                incrementCount[ inrement ] = dfs(y, x, count, table[y][x] );
                ++inrement;
            }
        }
    }

    return -1;
}

int main()
{
    freopen ("input.txt", "r", stdin);

    int test;
    cin >> test;

    int index = 0;
    while( test > index ){
        ++index;

        cin >> n;

        for ( int y = 0; y < n; ++y ){
            for ( int x = 0; x < n; ++x ){
                cin >> table[y][x];
                tableIncrement[y][x] = -1;
            }
            incrementCount[y] = 0;
        }
        cout << "#" << index << " " << solve();
    }

    return 0;
} 
  