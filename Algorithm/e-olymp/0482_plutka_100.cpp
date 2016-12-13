#include <stdio.h>
#include <iostream>

using namespace std;

#define M 3
#define N 31
#define SIZE_TABLE 1 << M

int calcTabel[SIZE_TABLE][SIZE_TABLE];


void makeCanTable(){
    int size = SIZE_TABLE;
    for ( int y = 0, x = size-1; y < size; ++y ){
        calcTabel[ y ][ x - y ] = true;
    }

    calcTabel[ 1 ][ 0 ] = true;
    calcTabel[ 4 ][ 0 ] = true;
    calcTabel[ 0 ][ 4 ] = true;
    calcTabel[ 0 ][ 1 ] = true;

    //for ( int y = 0; y < size; ++y ){
    //    for ( int x = 0; x < size; ++x ){
    //        cout << calcTabel[ y ][ x ] << " ";
    //    }
    //    cout << endl;
    //}
}
void solve(int n){
    int dp[N][SIZE_TABLE] = {0};
    dp[0][0] = 1;
    for ( int next_mask = 0; next_mask < SIZE_TABLE; ++next_mask ){
        dp[1][next_mask] = calcTabel[0][next_mask];
    }
    for ( int pos = 2; pos <= n; ++pos ){
        for ( int mask = 0; mask < SIZE_TABLE; ++mask ){
            for ( int next_mask = 0; next_mask < SIZE_TABLE; ++next_mask ){
                dp[pos][mask] += dp[pos - 1][next_mask] * calcTabel[mask][next_mask];
            }
        }
    }

    cout << dp[ n ][0] << endl;
}
int main()
{
    freopen("input.txt", "r", stdin);
    int T = 3;
    makeCanTable();
    int n;
    while ( cin >> n && n != -1 ){
        solve(n);
    }
    
    return 0;
}