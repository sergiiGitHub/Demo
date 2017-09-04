// Поиск наибольшей общей подпоследовательности

#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <iostream>
#include <string>
using namespace std;

string s1, s2;
const int N = 1001;
int Delta[ N ][ N ];

int main()
{
    //freopen ( "..//input.txt", "r", stdin);

    cin >> s1 >> s2; 
    int i = 0, j = 0;
    for ( ; i < s1.size(); ++i ){
        for (j = 0 ; j < s2.size(); ++j ){
            if ( s1.at( i ) == s2.at(j) ){
                Delta[ i ][ j ] = 1;
                if ( i != 0 && j != 0 ){
                    Delta[ i ][ j ] = Delta[ i ][ j ] + Delta[ i - 1 ][ j - 1 ];
                }
            } else {
                Delta[ i ][ j ] = 0;
                if (i > 0) Delta[i][j] = Delta[i - 1][j];
                if (j > 0 && Delta[i][j] < Delta[i][j - 1]) Delta[i][j] = Delta[i][j - 1];
            }
        }
        
    }
    cout << Delta[ i - 1 ][ j - 1 ] << endl;
	return 0;
}
