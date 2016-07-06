#include <iostream>
#include <stdio.h>
#include <vector>

using namespace std;

int e = 0;
int v = 0;
int count = 0;

vector< vector <int> > grap;
vector<bool> visted;

void read(){
    cin >> v;
    cin >> e;
    int v1 = 0, v2 = 0;
    visted.assign (v, false);
    for ( int i = 0; i < v; ++i ){
        vector<int> v;
        grap.push_back(v);
    }

    for ( int i = 0; i < e; ++i ){
        cin >> v1 >> v2;
        --v1;
        --v2;
        grap[v1].push_back(v2);
        grap[v2].push_back(v1);
    }
}

void dfs( int aFrom ){
    if ( visted[aFrom] ){
        return;
    }
    visted[ aFrom ] = true;
    for ( int i = 0; i < grap[ aFrom ].size(); ++i){
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
