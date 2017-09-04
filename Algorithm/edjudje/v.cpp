//https://89.209.12.198/cgi-bin/new-client?SID=8d5a8f6a635053ff&action=139&prob_id=22
//Обратный корень
/*input
1427
0
876652098643267843
5276538

2297.0716
936297014.1164
0.0000
37.7757
﻿*/

#include <stdio.h>
#include <iostream>
#include <math.h>  

using namespace std;
    
struct Node{
    double data;
    Node *next;

    Node(double data){
        this->data = data;
        next = NULL;
    }

    ~Node(){
        if(next != NULL){
            delete next;
            next = NULL;
        }
    }
};

struct ReverteLinkedList{
    Node *head;

    ReverteLinkedList(){
        head = NULL;
    }

    void push(double data){
        Node *next = head;
        head = new Node(data);
        head->next = next;
    }

    double pop(){
        Node *cur = head;
        head = head->next;

        double res = cur->data;
        cur->next = NULL;
        delete cur;
        return res;
    }

    bool isEmpty(){
        return head == NULL;
    }

    void print (){
        Node *cur = head;
        while(cur != NULL){
            cout << cur->data << " ";
            cur = cur->next;
        }
    }

    ~ReverteLinkedList(){
        delete head;
        head = NULL;
    }
};

double mySqrt(double x) {
    if (x <= 0)
        return 0;       // if negative number throw an exception?
    int exp = 0;
    x = frexp(x, &exp); // extract binary exponent from x
    if (exp & 1) {      // we want exponent to be even
        exp--;
        x *= 2;
    }
    double y = (1+x)/2; // first approximation
    double z = 0;
    while (y != z) {    // yes, we CAN compare doubles here!
        z = y;
        y = (y + x/y) / 2;
    }
    return ldexp(y, exp/2); // multiply answer by 2^(exp/2)
}

int main()
{
    //freopen("input.txt", "r", stdin);
    double n;
    ReverteLinkedList reverteLL;
    while ( cin >> n ){
        reverteLL.push(n);
        //printf("%0.4f \n",  mySqrt(n));
    }
    //reverteLL.print();

    while ( !reverteLL.isEmpty() ){
        //cout << reverteLL.pop() << "\n";
        printf("%0.4f \n", mySqrt(reverteLL.pop()) );
    }        
    
    return 0;
}

