#include "stdio.h"
#include <iostream>

using namespace std;

#define SIZE 50

int n;
bool graph [SIZE][SIZE];
bool visited [SIZE][SIZE];

void fill(){
    cin >> n;
    for ( int vF = 0; vF < n; ++vF ){
        for ( int vT = 0; vT < n; ++vT ){
            cin >> graph[vF][vT];
            visited[vF][vT] = 0;
        }
    }
}

bool dfs( int vF ){
    for ( int vT = 0; vT < n; ++vT ){
        if ( graph[vF][vT] == 0 ){
            continue;
        }

        if ( visited[vF][vT] ){
            return true;
        }

        visited[vF][vT] = 1;
        if ( dfs( vT ) ){
            return true;
        }
        visited[vF][vT] = 0;
    }
    return false;
}

bool solution(){
    for ( int vF = 0; vF < n; ++vF ){
        if ( dfs(vF) ){
            return 1;
        }
	}
	return 0;
}

int main(int argc, char** argv)
{
	//freopen("input.txt", "r", stdin);

	fill();
    cout << solution();
    return 0;
}

