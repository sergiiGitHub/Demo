#include <stdio.h>
#include <iostream>

#define MAX_SIZE 101

using namespace std;

int mArray[ MAX_SIZE ];
int mTypeSize = -1;
int arraySize = -1;
int i, j, maskSize, answer, currentAnswer;

void read(){
    //freopen("input.txt", "r", stdin);

    cin >> mTypeSize >> arraySize;
    i = 0;
    while ( i < arraySize ){
        cin >> mArray[ i ];
        ++i;
    }
    //fclose(stdin);
}

void solutionMask( ){
    maskSize = 1 << arraySize;
    answer = 0;
    currentAnswer = 0;
    for( i = 1; i < maskSize; ++i ){
        currentAnswer = 0;
        for( j = 0; j < arraySize; ++j ){
            if ( (i >> j) & 0x00000001 ){
                if ( ( currentAnswer + mArray[ j ] ) > mTypeSize ){
                    break;
                } else if ( ( currentAnswer + mArray[ j ] ) == mTypeSize ) {
                    answer = mTypeSize;
                    return;
                } else {
                    currentAnswer += mArray[ j ];
                }
            }
        }
        if ( answer < currentAnswer ){
            answer = currentAnswer;
        }
    }
}

int main(int argc, char* argv[])
{
    read( );
    solutionMask( );
    cout << "sum:" << answer << endl;

    return 0;
}

