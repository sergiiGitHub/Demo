//https://89.209.12.198/cgi-bin/new-client?SID=8d5a8f6a635053ff&action=139&prob_id=24
/*input 
5
10
0
output 
3
4
*/

﻿#include <stdio.h>
#include <iostream>
#include <math.h>  

#define SIZE 100001

using namespace std;

int ans[SIZE];
int maxAns[SIZE];

int solve(int q){

    if (ans[q] == -1){
        if (q%2 == 0){
            int prev = q / 2;
            ans[q] = solve(prev);       
        } else {
            int prev = (q - 1) / 2; //2i+1
            ans[q] = solve(prev) + solve(prev+1);
        }
    }
    
    maxAns[q] = ans[q];
    if (maxAns[q] < maxAns[q - 1]){
        maxAns[q] = maxAns[q - 1];
    }
    return ans[q];
}

void init(){
    ans[0] = 0;
    ans[1] = 1;
    maxAns[0] = 0;
    maxAns[1] = 1;
    for (int i = 2; i < SIZE; ++i ){
        ans[i] = -1;
        maxAns[1] = 0;
    }
}

int main()
{

    init();
    //freopen("input.txt", "r", stdin);
    int n;
    while ( cin >> n && n != 0){
        for (int i = 2; i <= n; ++i){
            solve(i);
        }
        //cout << "n: " << n << " = " << maxAns[n] << "\n";
        cout << maxAns[n] << "\n";
    }
    
    return 0;
}
