#include <iostream>
#include <stdio.h>
#include <ctime>

using namespace std;

#define MAX_STONE  20

int tableOrc[ MAX_STONE ] = { 0 };
int tablePrice[ MAX_STONE ] = { 0 };

int n = 0;
int resultBt;
int priceLocal;
int orc;
int zero;
int one;
int two;

void read(){
    cin >> n;
    for ( int i = 0; i < n; ++i ){
        cin >> tableOrc[ i ];
        cin >> tablePrice[ i ];
    }
}

void backTrackingSolution( int index, int aPay, int aZeroButle, int aOneButle, int aTwoButle ) {
    if ( index == n ){
        //cout << "aPay : " << aPay << endl << endl;
        if( aPay < resultBt ){
            resultBt = aPay;
        }
    } else {
        //buttle      
        if ( (aZeroButle + aOneButle + aTwoButle) >= tableOrc[index] ){
            orc = tableOrc[index];
            zero = aZeroButle;
            one = aOneButle;
            two = aTwoButle;
            if ( two < orc ){
                orc -= two;
            } else {
                orc = 0;
            }

            if ( one < orc ){
                orc -= one;
                one = 0;
            } else {
                one -= orc;
                orc = 0;
            }

            zero -= orc;

            two = one;
            one = zero;
            zero = 0;
            backTrackingSolution( index + 1, aPay, zero, one, two);
        }
        
        //hire
        priceLocal = aPay;
        priceLocal += tablePrice[index] << 1;
        if ( priceLocal < resultBt ){
            zero = aZeroButle;
            zero += tableOrc[index];
            backTrackingSolution( index + 1, priceLocal, zero, aOneButle, aTwoButle);
        }

        //buy
        priceLocal = aPay;
        priceLocal += tablePrice[index];
        if ( priceLocal < resultBt ){
            backTrackingSolution( index + 1, priceLocal, aZeroButle, aOneButle, aTwoButle);
        }
    }
}

int main()
{
    //freopen ("input.txt","r",stdin);
    read();

    //time_t rawtime, startTime;
    //time ( &startTime );
    
    resultBt = 0xFFFFFF;
    backTrackingSolution(0, 0, 0, 0, 0);
    //time ( &rawtime );
    //cout << "time: " << rawtime - startTime << endl;
    cout << resultBt <<  endl;

    return 0;
}
