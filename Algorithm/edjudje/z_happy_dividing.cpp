
#include <stdio.h>
#include <iostream>

using namespace std;

int mNumbers;
int happySize = 14;
int happyNumber[ ] = { 4, 7, 44, 47, 74, 77, 444, 447, 474, 477, 744, 747, 774, 777 };

void solution(  ){
    //freopen("input.txt", "r", stdin);
    cin >> mNumbers;
    for( int i = 0; i < happySize; ++i ){
        //cout << "v:" << happyNumber[ i ] << "; gdc:" << gcd( happyNumber[i], mNumbers ) << endl;
        if ( mNumbers % happyNumber[i] == 0 ){
            cout << "YES" << endl;
            return;
        }
        if ( mNumbers < happyNumber[i] ){
            break;
        }
    }
    cout << "NO" << endl;
    //fclose(stdin);
}

int main(int argc, char* argv[])
{
    solution();
    return 0;
}

