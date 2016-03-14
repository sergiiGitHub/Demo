// DFS.cpp : Defines the entry point for the console application.
//
/*

Input format

Первая строка содержит два числа разделенные пробелом – число задач N (1 ≤ N ≤ 100000) и K - число пар, которые описывают последовательность выполнения задач (1 ≤ K ≤ 100000). В следующей строке идет K пар чисел, описывающих связи между задачами. Вторая задача в паре должна быть выполнена после первой задачи (индексы задач представлены числами от 1 до N включительно).
Output format

Индексы задач в таком порядке, в каком они должны выполнятся, чтобы все условия были соблюдены. Если есть несколько правильных ответов можно вывести любой из них.

9 9
4 1 1 2 2 3 2 7 5 6 7 6 1 5 8 5 8 9

8 9 4 1 5 2 7 6 3

*/

#include <iostream>
#include <stdio.h>

using namespace std;

class LinkedList{
public:
    int data;
    LinkedList* next;

    LinkedList( int aData ){
        data = aData;
        next = NULL;
    }

    LinkedList( ){
        data = -1;
        next = NULL;
    }

    void append( int aData ){
        //cout << "append:: aData = " << aData << endl;
        LinkedList *newLl = new LinkedList( aData );
        if ( next == NULL ){
            next = newLl;
            return;
        }
        LinkedList* current = next;
        while( current->next != NULL ){
            current = current->next;
        }
        current->next = newLl;
    }

    void print(){
        LinkedList* current = next;
        while( current != NULL ){
            cout << current->data << " ";
            current = current->next;
        }
        cout << endl;
    }
};

LinkedList *graph;
int *result;
int vertex = 0;
int edge = 0;

void print(){
    for ( int i = 1; i < vertex; ++i ){
        LinkedList ll = graph[ i ];
        cout << "v = " << ll.data << "( o = "<< result[ i ]  <<" ) : "  ;
        ll.print();
    }
    cout << endl;
}

void read(){
    freopen ( "input.txt", "r", stdin);

    int v1, v2;
    cin >> vertex >> edge;
    vertex++;
    graph = new LinkedList[ vertex ];
    result = new int[ vertex ];

    for ( int i = 0; i < vertex; ++i ){
        graph[ i ].data = i;
        result[ i ] = -1;
    }

    for ( int i = 0; i < edge; ++i ){
        cin >> v1 >> v2;
        graph[ v1 ].append( v2 );
    }
    fclose (stdin);
}

void dfs(const int &vertex, int &count ){
    //cout << " vertex = " << vertex << "out[ vertex ] = " << out[ vertex ] << endl;
    if ( result[ vertex ] != -1 ){
        return;
    }

    LinkedList *curretntNext = graph[ vertex ].next;
    while ( curretntNext != NULL){
        dfs( curretntNext->data, count );
        curretntNext = curretntNext->next;
    }
    result[++count] = vertex;
}

void solve(){

    int count = 0;
    int v;
    for ( v = 1; v < vertex; ++v){
        dfs( v, count );
    }
    //print();
    for ( v = vertex-1; v >= 1; --v ){
        cout << result[ v ] << " ";
    }
    cout << endl;
}

int main()
{
    read();
    //print();
    solve();

    delete [] result;
    delete [] graph;
    return 0;
}

