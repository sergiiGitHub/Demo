#include <iostream>
#include <stdio.h>

using namespace std;

#define MAX_STONE  21
int array[ MAX_STONE ] = { 0 };
bool visted[ MAX_STONE ] = { 0 };
int n = 0;
int resultBt = 0xFFFFFF;

void read(){
    cin >> n;
    for ( int i = 0; i < n; ++i ){
        cin >> array[ i ];
    }
}

void backTrackingSolution( int index ) {

    if ( index == n ){
        int s1 = 0, s2 = 0;
        //cout << "i:" << index << " ";
        for ( int i = 0; i < n; ++i ){
            //cout << visted[i] << " ";
            if ( visted[i] ){
                s1 += array[ i ];
            } else {
                s2 += array[ i ];
            }
        }
        //cout << endl;
        int diff = s1 - s2;
        if ( diff < 0 ){
            diff = -diff;
        }
        if ( diff < resultBt ){
            resultBt = diff;
        }
    } else {
        visted[index] = 0;
        backTrackingSolution( index + 1 );
        visted[index] = 1;
        backTrackingSolution( index + 1 );
    }
}

int main()
{
    //freopen ("input.txt","r",stdin);
    read( );

    backTrackingSolution(0);
    cout << resultBt <<  endl;
    return 0;
}
