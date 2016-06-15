#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <iostream>

#define SIZE 101
#define DIR_SIZE 4

using namespace std;

int arr[SIZE][SIZE];

                        //  r ,d, l, u
int deltaX [ DIR_SIZE ] = { 1, 0, -1, 0 };
int deltaY [ DIR_SIZE ] = { 0, 1,  0, -1 };

void fill(int size){

    int index = 1;
    int y = 0;
    int x = 0;
    for ( int d = 0; d < DIR_SIZE;  ){
        while ( x < size && x >=0 && y < size && y >=0 && arr[y][x] == 0 ){
            arr[y][x] = index;
                        
            ++index;

            x += deltaX[d];
            y += deltaY[d];
        }

        //rollback
        x -= deltaX[d];
        y -= deltaY[d];

        ++d;
        if ( d == DIR_SIZE && index <= size*size){
            d = 0;
        }

        //next
        x += deltaX[d];
        y += deltaY[d];
    }

}


int main()
{
    //freopen ( "..//input.txt", "r", stdin);
    int n, i, j;
    cin >> n >> i >> j;
    --i;
    --j;

    
    fill( n );
    cout << arr[ i ][ j ];

    return 0;
}