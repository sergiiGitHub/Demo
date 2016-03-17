#include <stdio.h>
#include <iostream>

#define MAX_SIZE 100

using namespace std;

struct LinkedList{
    int data;
    LinkedList *next;

    LinkedList(){
        data = -1;
        next = NULL;
    }

    LinkedList( int aData){
        data = aData;
        next = NULL;
    }

    void append( int data ){
        LinkedList *newLinkedList = new LinkedList( data );
        if ( next == NULL ){
            next = newLinkedList;
            return;
        }
        LinkedList *currentLl = next;
        while ( currentLl->next != NULL ){
            currentLl = currentLl->next;
        }
        currentLl->next = newLinkedList;
    }

    void print( ){
        LinkedList *currentLl = this;
        while ( currentLl != NULL ){
            cout << currentLl->data << ", ";
            currentLl = currentLl->next;
        }
        cout << endl;
    }

    ~LinkedList(){
        //cout << "destruct " << data << endl;
        if ( next != NULL ){
            delete next;
        }
    }
};

struct Queue{
    LinkedList *head, *tail;

    Queue(){
        head = tail = NULL;
    }

    void push( int aData ){
        if ( head == NULL ){
            tail = head = new LinkedList( aData );
            return;
        }
        tail->append( aData );
        tail = tail->next;
    }

    bool isEmpty(){
        return head == NULL;
    }

    int front( ){
        if ( head == NULL ){
            return -1;
        }
        LinkedList remove = *head;
        head = head->next;

        remove.next = NULL;
        return remove.data;
    }

    void print(){
        if ( !isEmpty() ){
            head->print();
        }
    }

    ~Queue(){
        delete head;
    }
};

LinkedList* graph;
int path[ MAX_SIZE ];
int currentSize = -1;
int startIndex = -1;
int endIndex = -1;

void read(){
    //freopen("..\\input.txt", "r", stdin);

    cin >> currentSize >> startIndex >> endIndex;
    --startIndex;
    --endIndex;
    graph = new LinkedList[ currentSize ];
    int index, y, x;
    bool val;
    for ( y = 0; y < currentSize; ++y){
        path[ y ] = 0;
        graph[ y ].data = y;
        for ( x = 0; x < currentSize; ++x){
            cin >> val;
            if ( val ){
                graph[ y ].append( x );
            }
        }
    }
    //fclose(stdin);
}

void print(){
    int y;
    for (y = 0; y < currentSize; ++y){
        graph[y].print();
    }
    cout << "path " ;
    for (y = 0; y < currentSize; ++y){
        cout << path[y] << " ";
    }
    cout << endl;
}

void bfs( int &index ){
    Queue q;
    q.push(index);
    path[ index ] = 1;
    int from, to;
    LinkedList *ll;
    while (!q.isEmpty()){
        from = q.front();
        //cout << "from = " << from << endl;
        ll = graph[ from ].next;
        while ( ll != NULL ){
        //cout << "to = " << to << " path[to] = " << path[to] << "graph[ from ][ to ] = " << graph[ from ][ to ] << endl;           
            to = ll->data;
            ll = ll->next;
            if ( path[ to ] == 0 ){
                q.push( to );
                path[ to ] = path[ from ] + 1;
                if( to == endIndex ){
                    //path[startIndex] = -1;
                    return;
                }
            }
        }
    }
    path[endIndex] = 1;
}

int main(int argc, char* argv[])
{
    read( );
    //print( );
    bfs( startIndex );
    //print( );
    cout << path[endIndex] - 1 << endl;
    delete [] graph;
    return 0;
}
