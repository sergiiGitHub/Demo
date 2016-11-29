#include <stdio.h>
#include <iostream>

using namespace std;

/*
 * #input
 * 5
 * 3
 * 2 1 2
 * 5
 * 1 2 1 2 1
 * 5
 * 2 1 1 2 1
 * 8
 * 2 2 1 1 1 2 1 1
 * 8
 * 2 2 2 2 2 2 2 2
 * 
 * 
 * #output
 * #1 7
 * #2 12
 * #3 14
 * #4 35
 * #5 41
 * 
 */

#define SIZE 10

enum PatrolStation {
    GAS_CAR = 1,
    DISEL_CAR,
    GAS_STATION,
    DISEL_STATION,
};

int N, nGasCar, nDiselCar;
int GasStationPosition = 0, DiselStationPosition;
int petrolStation[SIZE];
bool mFilledCar[SIZE];
int result;

void bt( int fueledCar, int fueledGasCar, int fueledDiselCar, int moveCount, int currentPosition, bool filledCar[SIZE], int volume, PatrolStation from ){
    if ( moveCount > result ){
        return;
    }

    if ( fueledCar == N ){
        if ( moveCount < result ){
            result = moveCount;
        }
    } else {
        switch (petrolStation[currentPosition])
        {
        case GAS_CAR:
            ++fueledCar;
            if ( fueledCar == N ){
                if ( moveCount < result ){
                    result = moveCount;
                }
                return;
            }
            --volume;
            ++fueledGasCar;
            filledCar[ currentPosition ] = true;
            //move Gas station
            if ( fueledGasCar < nGasCar ){
                bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + currentPosition, GasStationPosition, filledCar, volume, GAS_CAR );
            }
            //move Disel station
            if ( fueledDiselCar < nDiselCar ){
                bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + (DiselStationPosition - currentPosition), DiselStationPosition, filledCar, volume, GAS_CAR );
            }

            if( volume != 0 && fueledGasCar < nGasCar){
                //move right
                for ( int i = currentPosition + 1; i < DiselStationPosition; ++i ){
                    if ( !filledCar[i] && petrolStation[ i ] == GAS_CAR ){
                        bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + (i - currentPosition), i, filledCar, volume, GAS_CAR );
                    }
                }
                //move left
                for ( int i = currentPosition - 1; i > 0; --i ){
                    if ( !filledCar[i] && petrolStation[ i ] == GAS_CAR){
                        bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + (currentPosition - i), i, filledCar, volume, GAS_CAR );
                    }
                }
            }
            filledCar[ currentPosition ] = false;
            break;
        case DISEL_CAR:
            ++fueledCar;
            if ( fueledCar == N ){
                if ( moveCount < result ){
                    result = moveCount;
                }
                return;
            }
            --volume;
            ++fueledDiselCar;
            filledCar[ currentPosition ] = true;
            //move Gas station
            if ( fueledGasCar < nGasCar ){
                bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + currentPosition, GasStationPosition, filledCar, volume, DISEL_CAR );
            }
            //move Disel station
            if ( fueledDiselCar < nDiselCar ){
                bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + (DiselStationPosition - currentPosition), DiselStationPosition, filledCar, volume, DISEL_CAR );
            }
            if( volume != 0 && fueledDiselCar < nDiselCar ){
                //move right
                for ( int i = currentPosition + 1; i < DiselStationPosition; ++i ){
                    if ( !filledCar[i] && petrolStation[ i ] == DISEL_CAR ){
                        bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + (i - currentPosition), i, filledCar, volume, DISEL_CAR );
                    }
                }
                //move left
                for ( int i = currentPosition - 1; i > 0; --i ){
                    if ( !filledCar[i] && petrolStation[ i ] == DISEL_CAR){
                        bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + (currentPosition - i), i, filledCar, volume, DISEL_CAR );
                    }
                }
            }
            filledCar[ currentPosition ] = false;
            break;
        case GAS_STATION:
            //move right
            if ( fueledGasCar < nGasCar ){
                for ( int i = currentPosition + 1; i < DiselStationPosition; ++i ){
                    if ( !filledCar[i] && petrolStation[ i ] == GAS_CAR ){//mistake posible forget to check by type
                        bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + (i - currentPosition), i, filledCar, 2, GAS_STATION );
                        //!!! MISTAKE posible put break
                    }
                }
            }
            //move Disel station
            if (fueledDiselCar < nDiselCar && from != DISEL_STATION ){
                bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + DiselStationPosition, DiselStationPosition, filledCar, 2, GAS_STATION );
            }
            break;
        case DISEL_STATION:
            //move left
            if ( fueledDiselCar < nDiselCar ){
                for ( int i = currentPosition - 1; i > 0; --i ){
                    if ( !filledCar[i] && petrolStation[ i ] == DISEL_CAR ){
                        bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + (currentPosition - i), i, filledCar, 2, DISEL_STATION );
                    }
                }
            }
            //move Gas station
            if (fueledGasCar < nGasCar && from != GAS_STATION ){ //MISTAKE if forget to put check will be time limit exid
                bt( fueledCar, fueledGasCar, fueledDiselCar, moveCount + DiselStationPosition, GasStationPosition, filledCar, 2, DISEL_STATION );
            }
            //!!Mistake recursion
            break;
        default:
            break;
        }
    }
}

void solve(){

    petrolStation[GasStationPosition] = GAS_STATION;
    mFilledCar[GasStationPosition] = true;
    nDiselCar = nGasCar = 0;
    cin >> N;
    DiselStationPosition = N + 1;

    for ( int n = 1; n < DiselStationPosition; ++n ){
        cin >> petrolStation[ n ];
        mFilledCar[n] = false;
        if ( petrolStation[ n ] == GAS_CAR ){
            ++nGasCar;
        } else {
            ++nDiselCar;
        }
    }
    petrolStation[ DiselStationPosition ] = DISEL_STATION;
    mFilledCar[ DiselStationPosition ] = true;
    //mistake: indexing


    bt( 0, 0, 0, 0, GasStationPosition, mFilledCar, 0, GAS_STATION );
}

int main()
{
    freopen("input.txt", "r", stdin);
    int T;
    cin >> T;
    for( int t = 0; t < T; ++t ){
        result = 0xffffff;
        solve();
        cout << "#" << t+1 << " " << result << endl;
    }

    return 0;
}
