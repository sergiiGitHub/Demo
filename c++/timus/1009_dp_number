//http://acm.timus.ru/problem.aspx?space=1&num=1009


#include <stdio.h>
#include <iostream>

using namespace std;

int n, k, answer, *cache;
//F(N) = (K-1)*(F(N-1)+F(N-2)),

int f( int aN ){
    if ( cache[aN] != -1 ) return cache[aN];

    if ( aN == 0 ) {
        cache[0] = 1;
    } else if ( aN == 1 ) {
        cache[1] = k - 1;
    } else {
        cache[ aN ] = ( k - 1 ) * ( f( aN - 1 ) + f( aN - 2 ) );
    }
    return cache[ aN ];
}


void solution(){
    //freopen("..\\input.txt", "r", stdin);
    cin >> n >> k;
    cache = new int[ n+1 ];
    for ( int i = 0; i < n+1; ++i ){
        cache[i] = -1;
    }
    cout << f(n) << endl;
    //fclose(stdin);
}

int main(int argc, char* argv[])
{
    solution();
    return 0;
}

