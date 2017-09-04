//Не спрашивай даму о возрасте
/*
https://89.209.12.198/cgi-bin/new-client?SID=8d5a8f6a635053ff&action=139&prob_id=25

input
A1A
output
22
*/


﻿#include <stdio.h>
#include <iostream>
#include <math.h>  

#define SIZE 1000001
int arr[SIZE];
int currentSize;
int maxValue;

using namespace std;

int getDecimal(int k){

    int kOther = k - 1;

    int ans = 0;
    int kPower = 1;
    for ( int i = currentSize - 1; i >= 0; --i){
        ans += (kPower * arr[i]) % kOther;
        ans %= kOther;
        kPower *= k;
        kPower %= kOther;
    }
    return ans;
}

int findK(){

    long ansK = -1;
    for ( int k = maxValue+1; k < 37; ++k ){
        long decimal = getDecimal(k);
        if( decimal % (k-1) == 0 ){
            //cout << "k: " << k << "; decimal: " << decimal << endl; 
            ansK = k;
            break;
        }    
    }
    return ansK;
}

void set(char c, int index){
    if ('0' <= c && c <= '9'){
        arr[index] = c - '0';
    } else {
        arr[index] = c - 'A' + 10;
    }
    if (arr[index] > maxValue){
        maxValue = arr[index];
    }

}

void print(){
    for (int i = 0; i < currentSize; ++i){
        cout << arr[i];
    }
    cout << "\n";
}

int main() {
    //freopen("input.txt", "r", stdin);
    currentSize = 0;
    maxValue = 0;
    char c;
    
    while (cin.get(c)){
        if ( c == '\n' ){
            break;
        }
        set(c, currentSize);
        ++currentSize;
    }
    //print();
    //initPowerArr(2);
    //cout << "power: " << getPower(2, 3) << getPower(2, 4) << getPower(2, 5) << "\n";
    int ans = findK();

    if (ans == -1){
        cout << "No solution.\n";
    } else {
        cout << ans << "\n";
    }

    return 0;
}
