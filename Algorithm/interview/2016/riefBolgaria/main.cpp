#include <iostream>
#include <stdio.h>

using namespace std;


/*
 *     
 * 0|     1
   1|    1 1
   2|   1 2 1
   3|  1 3 3 1
   4| 1 4 6 4 1
   -------------
      0 1 2 3 4
      
      
      in: r:4 c:1
      out:4 
      in: r:3 c:2
      out:3
   */
#define SIZE 10

int table[SIZE][SIZE];

void print(){
    for ( int y = 0; y < SIZE; ++y ){
        for ( int x = 0; x < SIZE; ++x ){
            cout << table[y][x] << "\t";
        }
        cout << endl;
    }
}

void build(){

    for(int i = 0; i < SIZE; ++i){
        table[i][0] = 1;
        table[0][i] = 1;
    }

    for(int y = 1; y < SIZE; ++y){
        for( int x = 1; x < SIZE; ++x ){
            table[y][x] = table[y - 1][x] + table[y][x-1];
        }
    }
}

int getValue( int r, int c ){

    int tr = r - c;
    if ( tr < 0 ){
        return -1;
    }

    return table[ tr ][ c ];
}

void test(){
    for ( int y = 0; y < SIZE; ++y ){
        for ( int x = 0; x < SIZE; ++x ){
            int value = getValue( x, y );
            if ( value != -1 ){
                cout << value << "\t";
            }
        }
        cout << endl;
    }
}

int main()
{
    //freopen( "input.txt", "r", stdin );
    build();
    print();
    test();
    int r, c;

    cout << "row: " << endl;
    cin >> r;
    cout << "column: " << endl;
    cin >> c;
    cout << getValue(r, c);

    return 0;
}
