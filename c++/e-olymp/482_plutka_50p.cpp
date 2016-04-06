#include <stdio.h>
#include <iostream>
#include <math.h>

using namespace std;

#define SIZE 6
const int graphSize[ SIZE ] = { 3, 2, 1, 2, 1, 1 };
const int graph[SIZE][3] = { { 1, 3, 5}, // 0
                       { 0, 4}, //1
                       //{ -1},//2
                       { 3},//3
                       { 0, 2},//4
                       //{ -1},//5
                       { 1},//6
                       { 0}//7
                    };

bool visited[ SIZE ] = { 0, 1, 0, 1, 0, 1  };
bool visited2[ SIZE ] = { 0 };


int w, count;
const int h = 3;

void dfs( int vertex, int depth ){
    //cout << "dfs: v:" << vertex << "; d:" << depth << endl;
    if ( depth == w ){
        if ( vertex == 0  ){
            ++count;
            //cout << "+1";
        }
        //cout << endl;
        return;
    }
    int node = 0;
    while ( node < graphSize[vertex] ){
        dfs( graph[vertex][node], depth+1);
        ++node;
    }
}

void dp(  ){
    bool isFirst = true;
    for( int i = 0; i < w; ++i ){
        for ( int j = 0; j < SIZE; ++j ){
            if ( isFirst ){
                if( visited[j] ){
                    ++count;
                    for ( int k = 0; k < graphSize[j]; ++k ){
                        visited2[ k ] = true;
                    }
                    visited[j] = false;
                }
            } else {
                if( visited2[j] ){
                    ++count;
                    for ( int k = 0; k < graphSize[j]; ++k ){
                        visited[ k ] = true;
                    }
                    visited2[j] = false;
                }
            }
        }
        isFirst = !isFirst;
    }
}

void solution(  ){
    //freopen("input.txt", "r", stdin);
    while ( true ){

        cin >> w;
        if ( w == -1 ){
            break;
        }
        count = 0;
        //cout << "w: " << w << endl;
        dfs( 0, 0);
        //dp();
        cout << count << endl;
    }
    //fclose(stdin);
}

int main(int argc, char* argv[])
{
    solution();
    return 0;
}
	
