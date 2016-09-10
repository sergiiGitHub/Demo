#include <iostream>
#include <stdio.h>

/*
input
12 10
..........
.???......
.???......
.??*???...
...????...
....?***?.
......???.
...??.....
...??.....
..........
..........
..........
out 
2
*/

using namespace std;


#define SIZE 1000


char table[SIZE][SIZE];

int tableBad[SIZE][SIZE];

int tableAccepted[SIZE][SIZE];


int Y, X;

char black = '*';
char white = '.';
char maybe = '?';

bool isAccept( int y, int x, int k ){
    int endY = y + k - 1;
    int endX = x + k - 1;
    if ( endX >= X || endY >= Y ){
        return 0;
    }
    return 0 == tableBad[endY][endX] + tableBad[endY - k][endX - k] - ( tableBad[endY - k][endX] +  tableBad[endY][endX - k] );
}

void build( int k ){
    for ( int y = 0; y < Y; ++y ){
        for ( int x = 0; x < X; ++x ){
            if ( x == 0 || y == 0 ){
                tableAccepted[ y ][ x ] = 0;
            } else {
                tableAccepted[ y ][ x ] = tableAccepted[ y - 1 ][ x ] + tableAccepted[ y ][ x - 1 ] - tableAccepted[ y - 1 ][ x - 1 ] + isAccept(y, x, k);
            }
        }
    }
}

int getValue( int y, int x ){
    if ( x < 0 || y < 0 ){
        return 0;
    } else {
        return tableAccepted[y][x];
    }
}

bool isPaint( int y, int x, int k ){
    return 0 < ( getValue(y, x) + getValue( y - k, x - k) - getValue( y - k, x) - getValue( y, x - k) );
}

bool isEveryPointPaint( int k ) {
    for ( int y = 0; y < Y; ++y ){
        for ( int x = 0; x < X; ++x ){
            if ( table[y][x] == black ){
                 if ( !isPaint( y, x, k ) ){
                     return false;
                 }
            }
        }
    }
    return true;
}

bool check( int k ){
    build( k );
    return isEveryPointPaint( k );
}

int main()
{
    //freopen ("input.txt", "r", stdin);


    cin >> Y >> X;

    for ( int y = 0; y < Y; ++y ){
        for ( int x = 0; x < X; ++x ){
            cin >> table[y][x];
            if ( y == 0 ){
                tableBad[ 0 ][ x ] = x + 1;
            } else if ( x == 0 ){
                tableBad[ y ][ 0 ] = y + 1;
            } else {
                tableBad[ y ][ x ] = tableBad[ y - 1 ][ x ] + tableBad[ y ][ x - 1 ] - tableBad[ y - 1 ][ x - 1 ] + (table[y][x] == white);

            }
        }
    }

    int res = 1;

    //do binary search

    //int K = min( Y, X );

    /*for( int k = res + 1; k < K - 1; ++k ){

        if ( check( k ) ){

            res = k;

        } else {

            break;

        }

    }*/

    int right = min( Y, X );
    int left = res;
    while( left < right ){
        int mid = (right + res) / 2;
        if ( check( mid ) ){
            left = mid + 1;
            res = mid;
        } else {
            right = mid;
        }
    }

    //cout << "c: " << check( 2 ) << endl;

    cout << res << endl;
    return 0;
}