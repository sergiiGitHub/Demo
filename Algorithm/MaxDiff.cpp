#include <stdio.h>
#include <iostream>

using namespace std;

#define SIZE 1000

int arr[SIZE];

/*
input
3
7
2 3 10 6 4 8 1
5
1 2 90 10 110
*/

/*
time complexity O(n*n)
Auxiliary Space O(1)
*/
int maxDiff(int arr_size)
{
    int max_diff = arr[1] - arr[0];
    int i, j;
    for(i = 0; i < arr_size; i++)
    {
        for(j = i+1; j < arr_size; j++)
        {
            //cout << "m1: " << arr[i] << "; m2: " << arr[j] << endl;
            int v = arr[j] - arr[i];
            if(v > max_diff){
                max_diff = v;
            }
        }
    }
    return max_diff;
}
/*
time complexity O(n)
Auxiliary Space O(n)
*/
int improveMaxDiff( int n, int arrMax[] ){

    int ans = 0;
    for( int i = 0; i < n; ++i ){
        int val = arrMax[ n - 1 ] - arrMax[ i - 1 ];
        if ( ans < val ){
            ans = val;
        }
    }
    return ans;
}

/* +++
time complexity O(n)
Auxiliary Space O(1)
*/
int improveMaxDiff2( int n ){

    int minItem = arr[0];
    int ans = arr[0] - arr[1];
    for( int i = 1; i < n; ++i ){
        int v = arr[i] - minItem;
        if ( ans < v ){
            ans = v;
        }
        if ( minItem > arr[i] ){
            minItem = arr[i];
        }
    }

    return ans;
}

/* +++
time complexity O(n)
Auxiliary Space O(1)
*/
int improveMaxDiff3(int arr[], int n)
{
    // Initialize diff, current sum and max sum
    int diff = arr[1]-arr[0];
    int curr_sum = diff;
    int max_sum = curr_sum;
 
    for(int i=1; i<n-1; i++)
    {
        // Calculate current diff
        diff = arr[i+1]-arr[i];
 
        // Calculate current sum
        if (curr_sum > 0)
           curr_sum += diff;
        else
           curr_sum = diff;
 
        // Update max sum, if needed
        if (curr_sum > max_sum)
           max_sum = curr_sum;
    }
 
    return max_sum;
}

int main()
{
    freopen("input.txt", "r", stdin);

    int t = 0, n = 0;
    cin >> t;
    for ( int i = 0; i < t; ++i ){
        int arrMax[SIZE];
        cin >> n;
        for ( int j = 0; j < n; ++j ){
            cin >> arr[ j ];
            cout << arr[ j ] << " ";
            arrMax[j] = arr[ j ];
            if ( j != 0 && arrMax[j - 1] > arrMax[j] ){
                arrMax[j] = arrMax[j - 1];
            }
        }
        cout << endl;

        cout << improveMaxDiff2(n) << endl;
        cout << maxDiff( n ) << endl;
    }

    return 0;
}
