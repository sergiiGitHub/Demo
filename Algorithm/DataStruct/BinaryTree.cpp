#include <iostream>
#include <stdio.h>

using namespace std;

struct Node{
    int data;
    Node* left;
    Node* right;
    Node( int data ){
        this->data = data;
        left = NULL;
        right = NULL;
    }

    ~Node() {
        delete left;
        left = NULL;
        delete right;
        right = NULL;
        cout << "destroy: " << data << endl;
    }
};

struct BinaryTree{
    Node* head = NULL;

    void insert( int data ){
        if ( head == NULL ){
            head  = new Node(data);
        } else {
            insert( head, data );
        }
    }

    int maxDepth(){
        return maxDepth( head );
    }

    void insert ( Node *node, int data ){
        cout << " insert r:" << node->data << "; c: " << data << endl;
        if (data <= node->data){
            if ( node->left == NULL ){
                node->left = new Node(data);
            } else {
                insert(node->left, data);
            }
        } else {
            if ( node->right == NULL ){
                node->right = new Node(data);
            } else {
                insert(node->right, data);
            }
        }
    }

    ~BinaryTree (  ){
        delete head;
        head = NULL;
    }

private:
    int maxDepth( Node* mynode ){
        if( mynode == NULL ){
            return 0;
        } else {
            int leftRes = maxDepth(mynode->left);
            int rightRes = maxDepth(mynode->right);
            return ( leftRes >= rightRes) ? leftRes + 1 : rightRes + 1;
        }
    }
};

int main()
{
    //freopen( "input.txt", "r", stdin );

    BinaryTree tree;
    tree.insert(1);
    tree.insert(0);
    tree.insert(2);
    tree.insert(3);
    cout << tree.maxDepth() << endl;
    return 0;
}
