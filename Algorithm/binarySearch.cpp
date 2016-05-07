#include <stdio.h>
#include <iostream>

int solutionArray[] = {1, 5, 16, 17, 34, 41, 59};

bool bs( unsigned int val ){
    int l = 0, r = SIZE-1, mid = ( r + l ) / 2;
    while( l < r ){
        mid = ( r + l ) / 2;
        if ( solutionArray[ mid ] == val ){
            return true;
        } else if ( solutionArray[ mid ] < val ){
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return false;
}

int main(int argc, char* argv[])
{
	cout << bs( 41 );
}
