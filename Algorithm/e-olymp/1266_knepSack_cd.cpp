#include <iostream>
#include <stdio.h>

using namespace std;

#define SIZE 200
#define SIZE_CD 100

int val[SIZE] = {200};
int wt[SIZE] = {200};
int K[SIZE_CD + 1][SIZE_CD + 1];

int max(int a, int b) { return (a > b)? a : b; }
 
int knapSack(int W, int wt[], int n)
{
   int i, w;
 
   for (i = 0; i <= n; i++)
   {
       for (w = 0; w <= W; w++)
       {
           if (i==0 || w==0)
               K[i][w] = 0;
           else if (wt[i-1] <= w)
                 K[i][w] = max(wt[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
           else
                 K[i][w] = K[i-1][w];
       }
   }
 
   return K[n][W];
}

int main()
{
    //freopen ("input.txt", "r", stdin);
    int W, s;
    while ( cin >> W >> s ){
        for ( int i = 0; i < s; ++i ) {
            cin >> wt[i];
        }
        cout <<"sum:" << knapSack(W, wt, s) << endl;
    }

    return 0;
}