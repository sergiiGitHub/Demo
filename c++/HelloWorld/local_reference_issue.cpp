#include <iostream>           // std::cout

/*
Reference to local variable
 
Compilation:
main.cpp: In function ‘A& create()’:
main.cpp:33:7: warning: reference to local variable ‘a’ returned [-Wreturn-local-addr]
     A a;
       ^
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
Output:
A()
~A()
A( const A& a )
-1
~A()
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
Result:
unpredict behavior
*/

using namespace std;

class A{
public:
    A(){
        cout << "A()" << endl;
    }

    void setValue( int aV ){
        v = aV;
    }

    int getValue( ){
        return v;
    }

    A( const A& a ) : v( a.v ) {
        cout << "A( const A& a )" << endl;
    }

    ~A(){
        v = -1;
        cout << "~A()" << endl;
    }
private:
    int v;
};

A& create(){ // Danger unpredict behavior
    A a;
    a.setValue(12);
    return a;
}

int main(){

    A a = create();
    //A a1(a);
    cout << a.getValue() << endl;
    return 0;
}
