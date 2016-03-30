//http://www.e-olymp.com/ru/problems/4445

#include <stdio.h>
#include <iostream>

using namespace std;



void read(){
    cin >> f;
}

int solution(  ){
    if ( arrayF[f-1] == -1 ){
        if ( f%2 ){
            arrayF[f-1] = f * arrayF[f-2];
        } else {
            arrayF[f-1] = f-2;
        }
    }
    return arrayF[f-1];
}

int main(int argc, char* argv[])
{

    /*for ( int i = 0; i < 10; ){
        f = ++i;
        //cout << i << ": " << solution() << endl;
        cout << some[f-1] << endl;
    }*/
    read();
    //cout << solution() << endl;
    cout << some[f-1] << endl;

    return 0;
}

