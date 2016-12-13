#include <iostream>
#include <stdio.h>

using namespace std;

#define SIZE 100

int traks[SIZE] = {100};
int sums[SIZE] = {0};
int n, s, r;

void bt( int index,  int sum ){

    if(n == r || sum > n){
        return;
    }

    if ( index == s ){
        if ( sum > r  ){
            r = sum;
        }
    } else {
        bt( index + 1, sum + traks[index] );
        bt( index + 1, sum );
    }
}

int main()
{
    freopen ("input.txt", "r", stdin);

    while ( cin >> n >> s ){
        for ( int i = 0; i < s; ++i ) {
            cin >> traks[i];
            if ( i == 0 ){
                sums[i] = traks[i];
            } else {
                sums[i] = traks[i] + sums[i - 1];
            }
        }
        r = 0;
        bt( 0, 0 );
        cout <<"sum:" << r << endl;
    }
}

