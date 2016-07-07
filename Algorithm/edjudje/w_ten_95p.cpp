//http://www.e-olymp.com/ru/problems/4445

#include <stdio.h>
#include <iostream>

using namespace std;

#define SIZE 65536
unsigned int table[ SIZE ];

void buildArray(){
    table[ 0 ] = 0;
    table[ 1 ] = 1;
    for( int i = 2; i < SIZE; ++i ){
        table[ i ] = ( i + 1 ) * i >> 1;
    }
    //int i = 0xFFFFFFFF;
    //cout << i << endl;
    //cout << table[ SIZE - 1 ] << endl;
}

bool bs( unsigned int val ){
    int l = 0, r = SIZE - 1, mid;
    while ( l < r ){
        mid = (r + l)/2;
        if( table[ mid ] == val ){
            return true;
        } else if ( table[ mid ] < val ){
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return false;
}

int main(int argc, char* argv[])
{
    buildArray();
    //freopen("input.txt", "r", stdin);
    int mNumbers;
    cin >> mNumbers;
    unsigned int val;
    while( 0 < mNumbers ){
        cin >> val;
        --val;
        cout << bs( val ) << " ";
        --mNumbers;
    }
    //fclose(stdin);
    return 0;
}

