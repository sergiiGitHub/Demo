#include <iostream>
#include <stdio.h>

using namespace std;
#define SIZE 101

int e = 0;
int v = 0;
int count = 0;

int grap[SIZE][SIZE];
int size[SIZE];
bool visted[SIZE];

void read(){
    cin >> v;
    cin >> e;
    int v1 = 0, v2 = 0;
    for ( int i = 0; i < v; ++i ){
        visted[i] = false;
        size[i] = 0;
    }

    for ( int i = 0; i < e; ++i ){
        cin >> v1 >> v2;
        --v1;
        --v2;
        grap[v1][size[v1]] = v2;
        grap[v2][size[v2]] = v1;
        ++size[v1];
        ++size[v2];
    }
}

void dfs( int aFrom ){
    if ( visted[aFrom] ){
        return;
    }
    visted[ aFrom ] = true;
    for ( int i = 0; i < size[aFrom]; ++i){
        dfs( grap[ aFrom ][ i ] );
    }
    ++count;
}

int main()
{
    freopen ("input.txt","r",stdin);
    read();
    count = 0;
    dfs( 0 );
    cout << ((count == v )?"YES":"NO") << endl;
    return 0;
}
