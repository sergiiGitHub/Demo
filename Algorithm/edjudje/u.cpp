/*1
5
1 3 0 4 4
2 3 0 0 4
2 3 3 2 4
2 0 0 2 4
4 4 4 4 4
5
0 0 0 0 0
0 0 0 0 0
1 0 1 0 1
0 0 0 0 0
0 0 0 0 0
5
4 4 0 0 1
2 0 5 0 5
2 2 1 2 0
0 0 4 1 5
0 1 4 4 2
*/


#include <iostream>
#include <stdio.h>

using namespace std;

#define SIZE 100
#define NUMBERS 6
#define pathSize 4

int table[ SIZE ][ SIZE ];
int n;

int inrement;
int tableIncrement[ SIZE ][ SIZE ];
int incrementCount[ SIZE * SIZE ];
bool incrementVisited[SIZE * SIZE ];

int pathX[ pathSize ] = { 0, 0,  1, -1 };
int pathY[ pathSize ] = { 1, -1, 0, 0 };

int dfs( int y, int x, int& count, int digit ){
    if ( tableIncrement[ y ][ x ] != -1 || digit != table[y][x] ){
        return count;
    }
    ++count;
    tableIncrement[ y ][ x ] = inrement;
    for ( int p = 0; p < pathSize; ++p ){
        int newX = x + pathX[p];
        if ( 0 <= newX && newX < n ){
            int newY = y + pathY[p];
            if ( 0 <= newY && newY < n ){
                dfs( newY, newX, count, digit );
            }
        }
    }
    return count;
}

void dfs_zero( int y, int x, int &increment, int* number ){

    if ( 0 != table[y][x] ){
        if ( incrementVisited[ tableIncrement[ y ][ x ] ] ){
            return;
        }
        incrementVisited[ tableIncrement[ y ][ x ] ] = true;
        int newCount = incrementCount[ tableIncrement[ y ][ x ] ];
        number[table[y][x]] += newCount;

        return;
    }

    if ( tableIncrement[y][x] != -1 ){
        return;
    }

    tableIncrement[y][x] = increment;
    for ( int p = 0; p < pathSize; ++p ){
        int newX = x + pathX[p];
        if ( 0 <= newX && newX < n ){
            int newY = y + pathY[p];
            if ( 0 <= newY && newY < n ){
                dfs_zero( newY, newX, increment, number );
            }
        }
    }
}

void dfs_subsitute( int y, int x, int digit ){
    if ( table[y][x] != 0 ){
        return;
    }

    table[y][x] = digit;
    for ( int p = 0; p < pathSize; ++p ){
        int newX = x + pathX[p];
        if ( 0 <= newX && newX < n ){
            int newY = y + pathY[p];
            if ( 0 <= newY && newY < n ){
                dfs_subsitute( newY, newX, digit );
            }
        }
    }
}

int getDigit(int number[]){
    int result = 1;
    for ( int i = 2; i < NUMBERS; ++i ){
        if ( number[ i ] > number[ result ] ){
            result = i;
        } else if ( number[ i ] == number[ result ] ){
            result = i;
        }
    }
    return result;
}

int solve(){
    inrement = 0;
    for ( int y = 0; y < n; ++y ){
        for ( int x = 0; x < n; ++x ){
            if ( table[y][x] != 0 && tableIncrement[y][x] == -1 ){
                int count = 0;
                incrementCount[ inrement ] = dfs(y, x, count, table[y][x] );
                ++inrement;
            }
        }
    }

    //for ( int y = 0; y < n; ++y ){
    //    for ( int x = 0; x < n; ++x ){
    //        cout << table[y][x] << " ";
    //    }
    //    cout << endl;
    //}
    //cout << endl;

    //for ( int y = 0; y < n; ++y ){
    //    for ( int x = 0; x < n; ++x ){
    //        cout << tableIncrement[y][x] << " ";
    //    }
    //    cout << endl;
    //}

    //int digit = -1;
    //int count = -1;
    //dfs_zero(y, x, count, digit );
    //cout << "d:" << digit << "; c: " << count << endl;
    for ( int y = 0; y < n; ++y ){
        for ( int x = 0; x < n; ++x ){
            if ( table[y][x] == 0 ){
                //continue;
                int number[ NUMBERS ] = { 0 };
                dfs_zero(y, x, inrement, number );
                for ( int index = 0; index < inrement; ++index ){
                    incrementVisited[index] = false;
                }
                ++inrement;
                int digit = getDigit(number);
                dfs_subsitute( y, x, digit );
            }
            //tableIncrement[y][x] = -1;
            //check 
        }
    }

    //for ( int y = 0; y < n; ++y ){
    //    for ( int x = 0; x < n; ++x ){
    //        cout << table[y][x] << " ";
    //    }
    //    cout << endl;
    //}
    //cout << endl;

    for ( int y = 0; y < n; ++y ){
        for ( int x = 0; x < n; ++x ){
            tableIncrement[y][x] = -1;
        }
    }

    inrement = 0;
    for ( int y = 0; y < n; ++y ){
        for ( int x = 0; x < n; ++x ){
            if ( tableIncrement[y][x] == -1 ){
                int count = 0;
                incrementCount[ inrement ] = dfs(y, x, count, table[y][x] );
                ++inrement;
            }
        }
    }

    return inrement;
}

int main()
{
    //freopen ("input.txt", "r", stdin);

    int test;
    cin >> test;

    int index = 0;
    while( test > index ){
        ++index;

        cin >> n;

        for ( int y = 0; y < n; ++y ){
            for ( int x = 0; x < n; ++x ){
                cin >> table[y][x];
                tableIncrement[y][x] = -1;

            }
            incrementCount[y] = 0;
            incrementVisited[y] = false;
        }
        cout << "#" << index << " " << solve() << endl;
    }

    return 0;
}
