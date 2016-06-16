#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <iostream>


using namespace std;

#define SIZE 9
int size = SIZE;

const int w[SIZE] = { 1, 2, 4, 8, 16, 32, 64, 128, 256};

bool t1[SIZE];
bool t2[SIZE];

int out;
int in;

void backtracking( int id ){
    if (id == size ){
        int c1 = 0, c2 = 0, s1 = 0, s2 = 0;
        //cout << "t1: ";
        //for( int i = 0; i < size; ++i ){
        //    cout << t1[i] << " " ;
        //}
        //cout << endl;
        //cout << "t2: ";
        //for( int i = 0; i < size; ++i ){
        //    cout << t2[i] << " " ;
        //}
        //cout << endl<< endl;
        for( int i = 0; i < size; ++i ){
            if( t1[i] ){
                ++c1;
                s1 += w[i];
            } 
            if ( t2[i] ){
                ++c2;
                s2 += w[i];
            }

            int diff = s1 - (in + s2);
            if ( diff == 0 ){
                int s1 = c1 + c2;
                if ( s1 < out ) {
                    out = s1;
                }
            }
        }
    } else {
        t1[id] = false;
        for ( int i = 0; i < 2; ++i){
            t1[id] = !t1[id];
            t2[id] = false;
            for ( int j = 0; j < 2; ++j){
                t2[id] = !t2[id];
                if ( t2[id] && t1[id] ){
                    continue;
                }
                backtracking(id+1);
            }
        }
    }
}

int solve( int in ) {
    out = SIZE;
    backtracking(0);
    return out;
}

int main()
{
    //freopen ( "input.txt", "r", stdin);
    while( cin >> in ){
        cout << solve( in ) << endl;
    }
    
    return 0;
}