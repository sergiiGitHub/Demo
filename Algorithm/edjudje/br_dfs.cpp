#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <iostream>

#define SIZE 51
#define DIR_SIZE 4
#define DIR_SIZE_HALF (DIR_SIZE/2)

using namespace std;

int table[SIZE][SIZE];
int visted[SIZE][SIZE];
                   //  u   d  r    l   
int dirX[DIR_SIZE] = {  0, 0, 1,  -1, };
int dirY[DIR_SIZE] = { -1, 1, 0,   0, };

int h, w;
int startX, startY;

void fill( int w, int h){
    for ( int y = 0; y < h; ++y ){
        for( int x = 0; x < w; ++x ){
            cin >> table[y][x];
            if ( table[y][x] == 2 ){
                startX = x;
                startY = y;
            }
        }
    }
}

bool dfs( int startX, int startY, int maxHeight ){
    if( visted[ startY ][ startX ] ){
        return false;
    }
    
    visted[ startY ][ startX ] = true;
    int localHeight = 1;
    for ( int d = 0; d < DIR_SIZE; ++d ){
        int newY = startY + dirY[d] * localHeight;
        int newX = startX + dirX[d];
        if ( 0 <= newX && newX < w && 0 <= newY && newY < h ){
            if (table[ newY ][newX] == 0 ){
                if( localHeight < maxHeight ){
                    --d;
                    ++localHeight;
                } else {
                    localHeight = 1;
                }
                continue;
            }

            if ( table[ newY ][newX] == 3 ){
                return true;
            }

            if ( dfs( newX, newY, maxHeight ) ){
                return true;
            }
        }
    }   
    visted[ startY ][ startX ] = false;
    return false;
}

int main()
{

    //freopen ( "input.txt", "r", stdin);

    cin >> h >> w;

    fill(w, h);

    int result = 1;
    while( !dfs( startX, startY, result ) ){
        ++result;
        if ( result == h ){
            break;
        }
    }
    cout << result;

    return 0;
}