#include <stdio.h>
#include <iostream>

using namespace std;

struct Node{
    int data;
    Node *next;

    Node( int aData ){
        data = aData;
        next = NULL;
    }

    void append( int aData ){
        cout << "append:" << aData;
        Node *current = this;
        while ( current->next != NULL ){
            current = current->next;
        }
        current->next = new Node( aData );
        cout << " y "<< endl;
    }

    ~Node(){
        delete next;
        //cout << "destruct " << data << endl;
    }

};

struct LinkedList{
    Node *head;

    LinkedList(){
        head = new Node(-1);
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

int main(int argc, char* argv[])
{
    int i = 1;
    LinkedList ll(0);
    while( i < 6 ){
        ll.append(i);
        ++i;
    }
    ll.print();
    ll.revert();
    ll.print();
    return 0;
}

