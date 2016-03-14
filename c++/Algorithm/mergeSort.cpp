#include <iostream>
#define SIZE 8

using namespace std;

int testArray[ SIZE ] = { 2, 3, 8, 1, 4, 6, 5, 7 };
int buffer[ SIZE ];

void justMergeAditionMemory( int left, int mid, int right ){
    int *t = new int[right - left + 1];
    int k = 0, i = left, j = mid + 1;
    while (i <= mid && j <= right)
    {
        t[k++] = testArray[testArray[i] < testArray[j] ? i++ : j++];
    }
    while (i <= mid) t[k++] = testArray[i++];
    for (int q = 0; q < k; ++q) testArray[q + left] = t[q];
    delete[] t;
}

void justMerge( int left, int mid, int right ){
    int localSize = right - left;
    int k = 0, i = left, j = mid + 1;
    while (i <= mid && j <= right){
        if ( testArray[i] < testArray[j] ){
            buffer[ k ] = testArray[ i ];
            ++i;
        } else {
            buffer[ k ] = testArray[ j ];
            ++j;
        }
        ++k;
    }
    while( i <= mid ){
        buffer[ k ] = testArray[ i ];
        ++k;
        ++i;
    }
    for( i = 0; i < k; i++ ){
        testArray[left + i] = buffer[ i ];
    }
}

void mergeSort( int left, int right ){
    cout << "left = " << left << "right = " << right << endl;
    if ( right - left < 1 ) return;
    int mid = (left + right) / 2;
    mergeSort( left, mid );
    mergeSort( mid+1, right );
    justMerge( left, mid, right );
}

void print()
{
    for( int i = 0; i < SIZE; ++i ){
        cout << testArray[i] << " ";
    }
}

int main()
{
    cout << "Merge Sort example" << endl;

    print();
    cout << endl;
    mergeSort( 0, SIZE - 1 );
    print();

    return 0;
}

