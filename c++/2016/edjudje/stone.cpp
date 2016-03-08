#include <iostream>
#include <stdio.h>

#define MAX_STONE  21
int array[ MAX_STONE ] = { 0 };
int stoneNumber = 0;
using namespace std;

void read(){
    cin >> stoneNumber;
    cout << " stoneNumber = " << stoneNumber << endl;

    for ( int i = 0; i < stoneNumber; ++i ){
        cin >> array[ i ];
    }
}

int maskSolution(){
    int a,b;
    int result = 0xFFFFFF;
    int maskSize = 1 << ( stoneNumber - 1);

    for ( int i = 1; i < maskSize; ++i ){
        a = b = 0;
        for ( int j = 0; j < stoneNumber; ++j ){
            if ( i >> j & 0x01 ){
                a += array[ j ];
            } else {
                b += array[ j ];
            }
        }
        b -= a;
        if ( b < 0 ){
            b = -b;
        }
        if ( result > b ){
            result = b;
        }
    }
    return result;
}

int main()
{
    freopen ("input.txt","r",stdin);
    read( );

    cout << "result = " << maskSolution() <<  endl;
    fclose (stdin);
    return 0;
}

