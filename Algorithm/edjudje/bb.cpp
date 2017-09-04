//https://89.209.12.198/cgi-bin/new-client?SID=ae80c42d229a57e3&action=139&prob_id=28
//BB
// input 10
// output 25


#include <stdio.h>
#include <iostream>

using namespace std;

#define SIZE 9
int table[SIZE];
int totalDepth;
long result;

long getNewValue(){

    //sort
    int n = totalDepth, swap;
    for (int c = 0 ; c < ( n - 1 ); c++){
        for (int d = 0 ; d < n - c - 1; d++){
            if (table[d] > table[d+1]){ /* For decreasing order use < */
                swap = table[d];
                table[d] = table[d+1];
                table[d+1] = swap;
            }
        }
    }

    long res = 0;
    for (int i = 0; i < totalDepth; ++i){
        res *= 10;
        res += table[i];
    }
    return res;
}

bool solve(int val, int depth){
    if (val == 0 ){
        return false;
    }
    totalDepth = depth + 1;
    for (int i = 9; i > 1; --i){
        if (val % i == 0){
            int next = val / i;
            if (next == 1){
                table[depth] = i;
                return true;
            }
            if (solve(next, depth+1)){
                table[depth] = i;
                if (depth == 0){
                    result = getNewValue();
                }
                return true;
            }
        }  
    }
    return false;
}

int main(int argc, char* argv[])
{
    //freopen("input.txt", "r", stdin);
    unsigned int val;
    cin >> val;
    if (val < 10){
        cout << 10 + val << endl;
    } else {
        if (solve(val, 0)){
            cout << result << endl;
        } else {
            cout << -1 << endl;
        }
    }
    //fclose(stdin);
    return 0;
}

