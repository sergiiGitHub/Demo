#include <stdio.h>
#include <iostream>

#define MAX_SIZE 100

using namespace std;

int graph[ MAX_SIZE ][ MAX_SIZE ];
int width = -1;
int height = -1;
int x, y, index, newX, newY, oldX, oldY, path;
char val;
int result = 0;
int pathSize = 4;
int pathX[ ] = { 0, 0, -1, 1 };
int pathY[ ] = { -1, 1, 0, 0 };

void read(){
    //freopen("input.txt", "r", stdin);

    cin >> height >> width;
    for ( y = 0; y < height; ++y){
        for ( x = 0; x < width; ++x){
            cin >> val;
            if ( val == '#' ){
                graph[ y ][ x ] = 0;
            } else {
                graph[ y ][ x ] = -1;
            }
        }
    }
    //fclose(stdin);
}

void print(){
    int y, x;
    for (y = 0; y < height; ++y){
        for ( x = 0; x < width; ++x){
            cout << graph[ y ][ x ] << "\t";
        }
        cout << endl;
    }
}

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

void bfs( int &aIndex ){
    Queue q;
    q.push(aIndex);
    while (!q.isEmpty()){
        index = q.front();
        //cout << "from = " << from << endl;
        oldX = index % width;
        oldY = index / width;
        for ( path = 0; path < pathSize; ++path ){
        //cout << "to = " << to << " path[to] = " << path[to] << "graph[ from ][ to ] = " << graph[ from ][ to ] << endl;
            newX = oldX + pathX[ path ];
            newY = oldY + pathY[ path ];
            if ( 0 <= newX && newX < width
                && 0 <= newY && newY < height ){
                if ( graph[ newY ][ newX ] == 0 ){
                    q.push( ( newY *width + newX ) );
                    graph[ newY ][ newX ] = result;
                }
            }
        }
    }
}

int main(int argc, char* argv[])
{
    read( );
    //print( );
    for (y = 0; y < height; ++y){
        for ( x = 0; x < width; ++x){
            if ( graph[ y ][ x ] == 0 ){
                ++result;
                index = y * width + x;
                bfs( index );
            }
        }
    }

    cout << result << endl;
    return 0;
}
