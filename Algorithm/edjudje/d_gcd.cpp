// aps02.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>

using namespace std;

int gcd( int a, int b );
int localAbs( int a );

struct Point {
    int x;
    int y;
};

int main(int argc, char* argv[])
{
    //FILE *stream;
	//freopen_s(&stream, "inp.txt", "r", stdin);

    const int MAX_ROBOT_NUMBER = 5001;
    const int MAX_MAP_SIZE = 1001;
    short map[MAX_MAP_SIZE][MAX_MAP_SIZE] = {0}; // ???
    //int __g[MAX_MAP_SIZE][MAX_MAP_SIZE] = {0};
    Point robots[MAX_ROBOT_NUMBER];

    int mapSize = 0;
    int nRobotNumber = 0;

    cin >> mapSize >> nRobotNumber;

    int index = 1;
    while ( (index) <= nRobotNumber ){
        cin >> robots[index].x >> robots[index].y;
        index++;
    }

    int answer = 0;
    for ( int i = 1; i <= nRobotNumber; i++ ){
        for ( int j = 1; j <= nRobotNumber; j++ ){
            if ( i == j ){
                continue;
            }
            int normalizeX = localAbs(robots[ i ].x - robots[ j ].x);
            int normalizeY = localAbs(robots[ i ].y - robots[ j ].y);
            int gdcValue = gcd( normalizeX, normalizeY );
            normalizeX /= gdcValue;
            normalizeY /= gdcValue;
            normalizeX = robots[i].x + normalizeX * ((robots[j].x > robots[i].x) ? 1 : -1);
            normalizeY = robots[i].y + normalizeY * ((robots[j].y > robots[i].y) ? 1 : -1);
            if ( map[normalizeX][normalizeY] != i ){
                map[normalizeX][normalizeY]  = (short)i;
                answer++;
            }
        }
    }
    cout << answer << endl;

    return 0;
}

int gcd( int a, int b ){
    if ( b==0 ){
        return a; 
    }
    return gcd( b, a%b );
}

int localAbs( int a ){
    return ( a < 0 ) ? -a : a;
}

