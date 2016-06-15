#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <iostream>
#include <queue>
#include <string>
#define SIZE 10000
using namespace std;

int in;
int out;
int parent[SIZE] = { 0 };

int increase( int from){
	int res = from / 1000;
	if (res == 9){
		return -1;
	} else {
		++res;
	}
	res *= 1000;
	res += from % 1000;
	return res;
}

int decrease(int from){
    int res = from % 10;
    if ( res == 1 ){
        return -1;
    }
    --from;
    return from;
}

int shiftLeft(int from){
    int res = from / 1000;
    res += ( from - res * 1000 ) * 10;  
	return res;
}

int shiftRight(int from){
	return ( from / 10 + (from % 10) *1000 );
}

void addToParent(queue< int >& q, int& to, int& from){
	if (to != -1){
		if (parent[to] == 0){
			parent[to] = from;
            q.push(to);
		}
	}
}

void bfs(){

	queue< int > q;
	q.push(in);
	int to;

	while(!q.empty()){
		int from = q.front();
		q.pop();

		if ( from == out ){
			return;
		}

		to = increase( from );
		addToParent(q, to, from);
		to = decrease(from);
		addToParent(q, to, from);
		to = shiftLeft(from);
		addToParent(q, to, from);
		to = shiftRight(from);
		addToParent(q, to, from);
	}
}

void dispaly( int pointer ){
    if ( parent[ pointer ] != 0 && pointer != in ){
        dispaly( parent[ pointer ] );
    }
    cout << pointer << endl;
}

int main()
{
    //freopen ( "..//input.txt", "r", stdin);

	cin >> in >> out;
    bfs();

    dispaly( out );
	return 0;
}