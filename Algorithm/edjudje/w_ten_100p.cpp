//https://89.209.12.198/cgi-bin/new-client?SID=ae80c42d229a57e3&action=139&prob_id=23
// W

#include <stdio.h>
#include <iostream>
#include <math.h>

using namespace std;

bool solve(int v){
    double sd = sqrt(1 + 8l * v);

    if ( (sd - (long)sd) < 0.000000001 ){
        return true;
    } else {
        return false;
    }
}

int main(int argc, char* argv[]){
    //freopen("input.txt", "r", stdin);
    
    int mNumbers;
    cin >> mNumbers;
    unsigned int val;
    while( 0 < mNumbers ){
        cin >> val;
        --val;
        cout << solve( val ) << " ";
        --mNumbers;
    }
    
    //fclose(stdin);
    return 0;
}

