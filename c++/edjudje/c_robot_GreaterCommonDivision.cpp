// aps02.cpp : Defines the entry point for the console application.
/*

Problem C-Роботы 1
Роботы 1
Time limit: 1 s 
Memory limit: 64 M 

На поле квадратной формы, которое состоит из R*R (R = 109) клеток, находятся R*R роботов (по одному роботу в каждой клетке). Каждый робот находится ровно в центре клетки и его размер существенно меньше размера клетки. Для заданной пары роботов найти количество роботов, которые находятся строго между ними на линии, соединяющей этих двух роботов. 

Input format
Первая строка содержит 4 числа: координаты робота A и координаты робота Б. 

Output format
Количество роботов между заданными роботами на прямой, которая их соединяет. 

Notes
Пример: 

.....
.....
....3
.....
1...2

Между роботами 1 (1,1) и 2 (5,1) находится 3 робота (2,1), (3,1), (4,1).

Между роботами 1 (1,1) и 3 (5,3) находится 1 робот (3,2).

Input Output 
1 1 5 1
 3
 
5 3 1 1
 1
 

*/

//

#include <stdio.h>
#include <iostream>

using namespace std;

long gdc( long a, long b );
long localAbs( long a );

int main(int argc, char* argv[])
{

	long p1X, p1Y, p2X, p2Y;
	cin >> p1X >> p1Y >> p2X >> p2Y;

    //normalize
    long x = localAbs(p2X - p1X);
    long y = localAbs(p2Y - p1Y);

	cout << gdc( x, y ) - 1 << endl;
}

long gdc( long a, long b ){
    if ( b==0 ){
        return a; 
    }
    return gdc( b, a%b );
}

long localAbs( long a ){
    return ( a < 0 ) ? -a : a;
}



