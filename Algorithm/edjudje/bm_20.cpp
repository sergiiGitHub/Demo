#include <iostream>
#include <stdio.h>

using namespace std;

#define MAX_STONE  20
int tableOrc[ MAX_STONE ] = { 0 };
int tablePrice[ MAX_STONE ] = { 0 };
int action[ MAX_STONE ] = { 0 };

int HIRE = 0;
int BUTLE = 1;
int PAY = 2;

int n = 0;
int resultBt;

void read(){
    cin >> n;
    for ( int i = 0; i < n; ++i ){
        cin >> tableOrc[ i ];
        cin >> tablePrice[ i ];
    }
}

void backTrackingSolution( int index, int hired ) {
    if ( index == n ){
        int curentPay = 0;
        int zeroButle = 0;
        int oneButle = 0;
        int twoButle = 0;

        for( int i = 0; i < n; ++i ){
            if( action[i] == HIRE ){
                //cout << "HIRE: " << endl;
                curentPay += tablePrice[ i ] * 2;
                zeroButle  += tableOrc[i];
            } else if ( action[i] == BUTLE ){
                //cout << "BUTLE : " << endl;
                int orc = tableOrc[i];
                int general = oneButle + twoButle + zeroButle;
                if ( general < orc ){
                    //cout << "FS .............." << endl;
                    return;
                }

                //cout << "before: " << " 0: " << zeroButle << "; 1: " << oneButle << "; 2: " << twoButle << endl;
                if ( twoButle < orc ){
                    orc -= twoButle;
                } else {
                    orc = 0;
                }

                if ( oneButle < orc ){
                    orc -= oneButle;
                    oneButle = 0;
                } else {
                    oneButle -= orc;
                    orc = 0;
                }

                zeroButle -= orc;

                twoButle = oneButle;
                oneButle = zeroButle;
                zeroButle = 0;
                //cout << "after: " << " 0: " << zeroButle << "; 1: " << oneButle << "; 2: " << twoButle << endl;
            } else if ( action[i] == PAY ) {
                //cout << "PAY: " << endl;
                curentPay += tablePrice[ i ];
            } else {
                //cout << "NOTHING: " << endl;
            }
        }

        //cout << "curentPay : " << curentPay << endl << endl;
        if( curentPay < resultBt ){
            resultBt = curentPay;
        }
    } else {
        for ( int i = 0; i < 3; ++i ){
            if( i == HIRE ){
                action[index] = HIRE;
            } else if ( i == BUTLE ){
                //if ( hirred < tableOrc[ index ] ){
                //    continue;
                //}
                action[index] = BUTLE;
            } else {
                action[index] = PAY;
            }
            backTrackingSolution( index + 1, hired);
        }
    }
}

int main()
{
    //freopen ("input.txt","r",stdin);
    read();
    resultBt = 0xFFFFFF;
    backTrackingSolution(0, 0);
    cout << resultBt <<  endl;
    return 0;
}
