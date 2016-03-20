#include <stdio.h>
#include <iostream>

using namespace std;

struct Node{
    int data;
    Node *next;

    Node( ){
        data = -1;
        next = NULL;
    }

    Node( int aData ){
        data = aData;
        next = NULL;
    }

    void append( int aData ){
        if ( data == -1 ){
            data = aData;
            return;
        }
        Node *current = this;
        while ( current->next != NULL ){
            current = current->next;
        }
        current->next = new Node( aData );
    }

    ~Node(){
        delete next;
        //cout << "destruct " << data << endl;
    }

};

struct LinkedList{
    Node *head;

    LinkedList(){
        head = new Node();
    }

    LinkedList( int aData ){
        head = new Node(aData);
    }

    void append( int aData ){
        head->append(aData);
    }

    void print( ){
        Node *current = head;
        while ( current != NULL ){
            cout << current->data << ", ";
            current = current->next;
        }
        cout << endl;
    }

    void revert(){
        Node *current = head, *prev = NULL, *next = NULL;
        while ( current->next != NULL ){
            next = current->next;
            current->next = prev;
            prev = current;
            current = next;
        }
        current->next = prev;
        head = current;
    }

    ~LinkedList(){
        delete head;
    }
};

struct Queue{
    Node *head, *tail;

    Queue(){
        head = tail = NULL;
    }

    void push( int aData ){
        if ( head == NULL ){
            tail = head = new Node( aData );
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
        Node remove = *head;
        head = head->next;

        remove.next = NULL;
        return remove.data;
    }

    ~Queue(){
        delete head;
    }
};

Node *graph;
int *path;
int y, startV, endV, vectorNumber, edge;

void connect( int v1, int v2 ){
    Node *node = &graph[v1];
    node->append(v2);
}

void read(){
    //freopen("input.txt", "r", stdin);

    cin >> vectorNumber >> edge;
    cin >> startV >> endV;
    --startV;
    --endV;
    graph = new Node[ vectorNumber];
    path = new int[ vectorNumber ];
    for ( y = 0; y < vectorNumber; ++y){
        path[ y ] = -1;
    }
    int v1, v2;
    for ( y = 0; y < edge; ++y){
        cin >> v1 >> v2;
        --v1;
        --v2;
        connect( v1, v2 );
        connect( v2, v1 );
    }
    //fclose(stdin);
}
/*
void print(){
    cout << "vectorNumber" << vectorNumber << " edge " << edge << endl;
    for (y = 0; y < vectorNumber; ++y){
        cout << "v:" << y << ";c ";
        graph[y].print();
    }
}
*/
bool bfs( int index ){
    Queue q;
    q.push(index);
    path[ index ] = -2;
    while (!q.isEmpty()){
        y = q.front();
        //cout << "from: " << from << endl;
        Node *node = &graph[y];
        while( node != NULL ){
            if ( node->data == -1 ){
                break;
            }
            if ( path[ node->data ] == -1 ){
                q.push( node->data );
                path[ node->data ] = y;
                if ( node->data == endV ){
                    return true;
                }
            }
            node = node->next;
        }
    }
    return false;
}

void printAnswer( const int &v, int &count  ){
    if( path[ v ] != -2 ){
        printAnswer( path[ v ], ++count );
        cout << v + 1 << " ";

    } else {
        cout << count << endl;
        cout << v + 1 << " ";
    }

}

int main(int argc, char* argv[])
{
    read();
    //print();
    int count = 0;
    if( bfs(startV) ){
        printAnswer( endV, count );
    } else {
        cout << -1 << endl;
    }

    delete [] graph;
    delete [] path;
    return 0;
}

