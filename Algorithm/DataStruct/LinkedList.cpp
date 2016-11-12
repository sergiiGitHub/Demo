//http://www.e-olymp.com/ru/problems/4445

#include <stdio.h>
#include <iostream>

using namespace std;

struct Node{
    int data;
    Node* next;

    Node( int aData ){
        data = aData;
    }

    ~Node(){
        delete next;
        cout << "~Node " << data << endl;
    }
};

struct LinkedList{
    Node *head = NULL;

    void append( int aData ){
        if ( head == NULL ){
            head = new Node(aData);
        } else {
            Node *c = head;
            while ( c->next != NULL ){
                c = c->next;
            }
            c->next = new Node(aData);
        }
    }

    void print(  ){
        Node *c = head;
        while( c != NULL){
            cout << c->data << " ";
            c = c->next;
        }
        cout << endl;
    }

    void reverse(){
        if ( head == NULL ){
            return;
        }
        Node *p, *c, *n;
        p = NULL;
        c = head;
        while ( c->next != NULL ){
            n = c->next;
            c->next = p;
            p = c;
            c = n;
        }
        c->next = p;
        head = c;
    }

    ~LinkedList(){
        delete head;
    }
};

int main(int argc, char* argv[])
{
    LinkedList ll;
    for( int i = 0; i < 5; ++i ){
        ll.append(i);
    }
    ll.print();
    ll.reverse();
    ll.print();
    return 0;
}

