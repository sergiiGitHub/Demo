#include <stdio.h>
#include <iostream>

#define MAX_SIZE 10000

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

    void append( int aData ){
        if ( next == NULL ){
            next = new LinkedList( aData  );
            return;
        }
        LinkedList *currentLl = next;
        while ( currentLl->next != NULL ){
            currentLl = currentLl->next;
        }
        currentLl->next = new LinkedList( aData  );
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
int startIndex = -1;
int endIndex = -1;
int value, a1, a2, a3, a4;

void read(){
    //freopen("..\\input.txt", "r", stdin);
    cin >> startIndex >> endIndex;
    //fclose(stdin);
}

void print(int &aSize){
    int y;
    for (y = 0; y < aSize; ++y){
        graph[y].print();
    }
    cout << "path " ;
    for (y = 0; y < aSize; ++y){
        cout << path[y] << " ";
    }
    cout << endl;
}


void subtrackt(int aVertex){
    value = aVertex - 2;
    if ( value > 0 ){
        graph[aVertex].append(value);
    }
}

void multiply(int aVertex){
    value = aVertex * 3;
    if ( value < 10000 ){
        graph[aVertex].append(value);
    }
}

void sum( int aVertex ){
    value = aVertex;
    value += ( aVertex ) % 10;
    if ( aVertex > 9 ){
       value += ( aVertex / 10 ) % 10;
    } if ( aVertex > 99 ){
       value += ( aVertex / 100 ) % 10;
    } if ( aVertex > 999 ){
       value += ( aVertex / 1000 ) % 10;
    }

    if ( value < 10000 ){
        graph[aVertex].append(value);
    }
}

void buidGraph( int aVertex ){
    //graph[aVertex].data = aVertex;
    subtrackt(aVertex);
    multiply(aVertex);
    sum(aVertex);
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
        buidGraph( from );
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
    graph = new LinkedList[ MAX_SIZE ];
    //buidGraph( );
    bfs( startIndex );
    cout << path[endIndex] - 1 << endl;
    delete [] graph;
    return 0;
}
