// ConsoleApplication3.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>
#include <queue>

#define MAX_SIZE 40

#define MAP MAX_SIZE*MAX_SIZE

using namespace std;

char graph[MAP];
int path[MAP];
int currentSize = -1;
char start = '@';
char endSymbol = 'X';
char avilible = '.';
char disable = 'O';
//----------------
char yes = 'Y';
char no = 'N';
char fillSymbol = '+';
int startIndex = -1;
int endIndex = -1;
int deltaSize = 4;
int deltaY[] = { 0, 0, 1, -1 };
int deltaX[] = { 1, -1, 0, 0, };

void read(){
    //freopen("..\\input.txt", "r", stdin);

    cin >> currentSize;
    char val;
    int index, y, x;
    for ( y = 0; y < currentSize; ++y){
        for ( x = 0; x < currentSize; ++x){
            cin >> val;
            index = y * currentSize + x;
            graph[index] = val;
            path[ index ] = -2;
            if (val == start){
                startIndex = index;
            }
        }
    }
    //fclose(stdin);
}

void print(){
    int y, x; 
    for (y = 0; y < currentSize; ++y){
        for ( x = 0; x < currentSize; ++x){
            cout << graph[y * currentSize + x];
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

void bfs( int &index ){
    Queue q;
    q.push(index);
    int currentFromX, currentFromY, from, newX, newY, i, newIndex;
    while (!q.isEmpty()){
        from = q.front();

        currentFromX = from % currentSize;
        currentFromY = from / currentSize;
        
        for ( i = 0; i < deltaSize; ++i ){
            newX = currentFromX + deltaX[i];
            newY = currentFromY + deltaY[i];
            if ((0 <= newX && newX < currentSize)
                && (0 <= newY && newY < currentSize)){
                newIndex = newY * currentSize + newX;
                if ( path[newIndex] < 0 ){
                    if (graph[newIndex] == avilible){
                        q.push(newIndex);
                        path[newIndex] = from;
                    } else if (graph[newIndex] == endSymbol) {
                        endIndex = newIndex;
                        path[newIndex] = from;
                        path[startIndex] = -1;
                        return;
                    }
                }
            }
        }
    }
}

void fill(){
    int next = endIndex;
    while( path[next] != -1 ){
        graph[ next ] = fillSymbol;
        next = path[next];
    }
}

int main(int argc, char* argv[])
{
    read();
    bfs(startIndex);
    if ( endIndex != -1 ){
        cout << yes << endl;
        fill();
        print();
    } else {
        cout << no << endl;
    }

    return 0;
}
