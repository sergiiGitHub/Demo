#include <stdio.h>
#include <iostream>

using namespace std;

#define M 3
#define SIZE_TABLE 1 << M

int calcTabel[SIZE_TABLE][SIZE_TABLE];
int dp[M][SIZE_TABLE];

int main()
{
    //freopen("input.txt", "r", stdin);
    int T = 3;
    //cin >> T;
    for ( int y = 0; y < SIZE_TABLE; ++y ){
        for ( int x = 0; x < SIZE_TABLE; ++x ){
            calcTabel[ y ][ x ] = true;
            for ( int i = 0; i < M; ++i ){
                calcTabel[ y ][ x ] &= !(((y >> i) & 1) & ( (x >> i) & 1));
            }
            calcTabel[ y ][ x ];
        }
    }

    for ( int y = 0; y < SIZE_TABLE; ++y ){
        for ( int x = 0; x < SIZE_TABLE; ++x ){
            cout << calcTabel[ y ][ x ] << " ";
        }
        cout << endl;
    }

    int N = 2;

    for ( int pos = 0; pos < N; ++pos ){
        for ( int next_mask = 0; next_mask < SIZE_TABLE; ++next_mask ){
            dp[pos][next_mask] = 0;
        }
    }
    dp[0][0] = 1;
    for ( int pos = 1; pos < N; ++pos ){
        for ( int mask = 0; mask < SIZE_TABLE; ++mask ){
            for ( int next_mask = 0; next_mask < SIZE_TABLE; ++next_mask ){
                dp[pos][mask] += dp[pos - 1][next_mask] * calcTabel[mask][next_mask];
            }
        }
    }

    cout << "ans: " << endl;
    for ( int pos = 0; pos < N; ++pos ){
        for ( int next_mask = 0; next_mask < SIZE_TABLE; ++next_mask ){
            cout << dp[pos][next_mask] << " ";
        }
        cout << endl;
    }
    cout << endl;

    return 0;
}
