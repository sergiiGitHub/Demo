#include <cstdio>
#include <iostream>
#include <queue>

using namespace std;

int vertex, edge, *childCounter, **graph, *vertex1, *vertex2, *weight, *result;
bool *visited;

void print(){
    for ( int i = 0; i < vertex; ++i ){
        cout << "v = " << i << "c = "
        for ( int j = 0; j < childCounter[i]; ++j ){
            << j << "] = " << graph[ i ][ j ] << "; ";
        }
        cout << endl;
    }
}

void destroyGraph()
{
    for (int i = 0; i < vertex; ++i)
    {
        delete[] graph[i];
    }
    delete[] graph;
    delete[] childCounter;
    delete[] visited;
    delete[] vertex1;
    delete[] vertex2;
    delete[] weight;
    delete[] result;
}

void read(){
    freopen ( "input.txt", "r", stdin);
    scanf("%d %d", &vertex, &edge);

    visited = new bool[ vertex ];
    childCounter = new int[ vertex ];
    result = new int[ vertex ];
    graph = new int*[ vertex ];

    vertex1 = new int[ edge ];
    vertex2 = new int[ edge ];
    weight = new int[ edge ];

    int maxInt = (1 << 31)-1;
    for (int i = 0; i < vertex; ++i){
        childCounter[i] = 0;
        visited[i] = false;
        result[i] = maxInt;
    }
    for (int i = 0; i < edge; ++i){
        scanf("%d %d %d", &vertex1[i], &vertex2[i], &weight[i]);
        --vertex1[i];
        --vertex2[i];
        childCounter[ vertex1[i] ]++;
    }
    for (int i = 0; i < vertex; ++i){
        int size = childCounter[ i ];
        graph[i] = new int[ size ];
    }

    for (int i = 0; i < edge; ++i){
        graph[ vertex1[i] ][ vertex2[i] ] = weight[i];
    }
    //fclose( stdin );
}

int bfs( int& start, int& end){

    queue< int > queue;
    queue.push(start);
    result[ start ] = 0;

    while (!queue.empty() ){
        int from = queue.front();
        //cout << "bfs :: from " << from << endl;
        visited[ from ] = true;
        queue.pop();
        for (int to = 0; to < childCounter[from]; ++to){
            if ( graph[from][to] == 0 ){
                continue;
            }
            if (!visited[ to ] ){
                int val = graph[from][to] + result[ from ];
                if ( result[ to ] > val ){
                    result[ to ] = val;
                }
                queue.push( to );
            }
        }
    }
    return result[ end ];
}

int main()
{
    read();
    print();
    int start = 0;
    int endValue = vertex-1;
    cout << bfs( start, endValue) << endl;

    //destroyGraph();
    return 0;
}

