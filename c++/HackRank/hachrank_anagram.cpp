#include <cstdio>
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

#define SIZE 26
using namespace std;

int solve( string& str ){
    int res = 0;
    int leftGistagram[ SIZE ] = { 0 };
    int rightGistagram[ SIZE ] = { 0 };
    const int size = str.size() >> 1;
    
    for ( int i = 0; i < size; ++i ){
        ++rightGistagram[ str[i] - 'a' ];
        ++leftGistagram[ str[str.size() - 1 - i ] - 'a' ];
    }
    for ( int i = 0; i < SIZE; ++i ){
        res += abs(leftGistagram[i] - rightGistagram[i]);
    }
    return res >> 1;
}

int main()
{

    string str;
    int testNumber;
    //freopen ( "..//input.txt", "r", stdin);
    cin >> testNumber;
    while( testNumber ){
        cin >> str;
        //cout << str.size() << endl;

        if ( str.size() % 2 ){
            cout << -1 << endl;
        } else {
            cout << solve( str ) << endl;
        }
        --testNumber;
    }
    
	return 0;
}
