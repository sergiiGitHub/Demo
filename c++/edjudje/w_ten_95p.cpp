//http://www.e-olymp.com/ru/problems/4445

#include <stdio.h>
#include <iostream>
#include <math.h>

#define SIZE 65536

using namespace std;

int mNumbers;
unsigned int solutionArray[ SIZE ];

// one will be at 1 2 4 7 11 16 22
// x(n) pointer where value == 1
// have sequance X(n) = (n*( n - 1 )/2) + 1
// => n^2 - n = 2( x(n) -1 )
//from Quadratic equation ax^2 + bx + c = 0
// n = (-(-1) + sqrt( 1 - 4 * 1 * ( -2( x(n) - 1 ) ))/
bool getValue( int aIndex ){
    long long power = 1ll + 8ll * ( aIndex - 1 );
    int val = sqrt( power );
    bool ans = false;
    if ( power - (val*val) == 0){
        ans = true;
    }
    cout << "i:" << aIndex
    << "; power:" << power << "; val:" << val << "; r:" << (1+val)/2
    << ";\t ans:" << ans << endl;
    return ans;
    //
    //unsigned int n = 1 + sqrt( 1 + 8 * ( aIndex - 1 ) );
    //cout << "aIndex:" << aIndex << "; n:"<<n<< endl;
    /*
    if ( n % 2 ){
        return 0;
    } else {
        return 1;
    }
    */
}


int getValueCorrect( int aIndex ){
    --aIndex;
    int index = 0;
    int increment = 1;

    while( index < aIndex ){
        index += increment;
        ++increment;
    }

    if ( index == aIndex ){
        return 1;
    } else {
        return 0;
    }
}

void test(){

    //int number 2097153
    mNumbers = 2147483647;//2097153
    getValue( mNumbers ) ;
    while( mNumbers < 2147483647){
        //cout << "i:" << mNumbers << " = " << getValue( mNumbers ) <<  endl;
        if ( getValue( mNumbers ) != getValueCorrect(mNumbers)){
            cout << "error at = " << mNumbers;
            return;
        }
        ++mNumbers;
    }
    cout << "OK";
}

void buildArray(){
    for ( int i = 1; i <= SIZE; ++i ){
        solutionArray[ i - 1 ] = i*( i - 1 )/2 + 1;
        //if ( i > SIZE - 5 ){
        //    cout << solutionArray[ i - 1 ] << " ";
        //}
    }
    //cout << endl;
}

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

int solution(  ){
    //freopen("input.txt", "r", stdin);
    cin >> mNumbers;
    unsigned int index;
    while( 0 < mNumbers ){
        cin >> index;
        //cout << getValue( index ) << " ";
        cout << bs( index ) << " ";
        --mNumbers;
    }
    //fclose(stdin);
}

int main(int argc, char* argv[])
{
    buildArray();
    solution();
    //test();
    return 0;
}

