
// ConsoleApplication3.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>

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

LinkedList *graph;
int *path, *answer;
int y, startV, endV, vectorNumber, edge;

void connect( int v1, int v2 ){
    LinkedList *ll = &graph[v1];
    if( ll->data == -1 ){
        ll->data = v2;
    } else {
        ll->append(v2);
    }
}

void read(){
	//freopen("input.txt", "r", stdin);

	cin >> vectorNumber >> edge;
	cin >> startV >> endV;
	--startV;
	--endV;
	graph = new LinkedList[ vectorNumber];
	path = new int[ vectorNumber ];
	answer = new int[ vectorNumber + 1 ];
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
        LinkedList *ll = &graph[y];
        while( ll != NULL ){
            if ( ll->data == -1 ){
                break;
            }
            if ( path[ ll->data ] == -1 ){
                q.push( ll->data );
                path[ ll->data ] = y;
                if ( ll->data == endV ){
                    return true;
                }
            }
            ll = ll->next;
		}
	}
	return false;
}

void printAnswer(){

    answer[ 0 ] = 1;
    answer[ 1 ] = endV + 1;
    y = endV;
    while( path[ y ] != -2 ){
        //cout << path[ x ] + 1 << ", ";
        answer[++answer[ 0 ]] = path[ y ] + 1;
        y = path[ y ];
    }
    cout << answer[ 0 ] << endl;
    while ( answer[ 0 ] > 0 ){
        cout << answer[ answer[ 0 ] ] << " ";
        --answer[ 0 ];
    }
    cout << endl;
}

int main(int argc, char* argv[])
{
	read();
	//print();

	if( bfs(startV) ){
        printAnswer();
    } else {
        cout << -1 << endl;
    }

	delete [] graph;
	delete [] path;
	delete [] answer;
	return 0;
}

