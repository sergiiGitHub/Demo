#include <stdio.h>
#include <iostream>
#include <queue>

#define MAX_SIZE 34

using namespace std;

int graph[MAX_SIZE][MAX_SIZE];
int currentSize = -1;
char avilible = '.';
//----------------
const int visited = 2;
const int deltaSize = 4;
int deltaY[] = { 0, 0, 1, -1 };
int deltaX[] = { 1, -1, 0, 0, };
int y, x, perimeter;

void read(){
	//freopen("input.txt", "r", stdin);

	cin >> currentSize;
	char val;
	for ( y = 0; y < currentSize; ++y){
		for ( x = 0; x < currentSize; ++x){
			cin >> val;
			if( val == avilible ){
                graph[y][x] = 1;
			} else {
                graph[y][x] = 0;
			}
		}
	}
	//fclose(stdin);
}

void print(){
    cout << currentSize << endl;
    for (y = 0; y < currentSize; ++y){
		for ( x = 0; x < currentSize; ++x){
			cout << graph[ y ][ x ];
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

bool calculatePerimeter( int &newX, int &newY ){
    if ( (0 <= newX && newX < currentSize )
     && ( 0 <= newY && newY < currentSize )){
        if( graph[newY][newX] == 1 ){
            return true;
        } else if ( graph[newY][newX] == visited ){
            return false;
        }
    }
    //wall
    ++perimeter;
    return false;
}

void bfs( int index ){
	Queue q;
	perimeter = 0;
	q.push(index);
    int currentFromX, currentFromY, from, newX, newY, i, newIndex;
	while (!q.isEmpty()){
		from = q.front();
		currentFromX = from % currentSize;
		currentFromY = from / currentSize;
		//cout << "from y:" << currentFromY << ", x:" << currentFromX << endl;
        for ( i = 0; i < deltaSize; ++i ){
			newX = currentFromX + deltaX[i];
			newY = currentFromY + deltaY[i];
			if ( calculatePerimeter( newX, newY ) ){
                q.push( newY * currentSize + newX );
                graph[newY][newX] = visited;
			}
		}
	}
}

int main(int argc, char* argv[])
{
	read();
	//print();
	graph[0][0] = visited;
	bfs(0);
	cout << (perimeter - 4)*9 << endl;
	return 0;
}
