// Постройка новых дорог
// https://89.209.12.198/cgi-bin/new-client?SID=8d5a8f6a635053ff&action=139&prob_id=13
// ConsoleApplication6.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>
#include <vector>
#include <set>
#include <stdlib.h>  

using namespace std;

vector< int > mParent;
set< int > mNeed;

void buildDsu(int size);
int find(int x);
bool unionDsu(int x, int y);

int main(int argc, char* argv[])
{

	//FILE *f;
	//freopen_s(&f, "inp.txt", "r", stdin);

	int vertex;
	int edge;
	cin >> vertex >> edge;

	buildDsu(vertex+1);
	int v1, v2;
	for (int i = 0; i < edge; i++){
		cin >> v1 >> v2;
		unionDsu(v1, v2);
		int tempt = find(v1);
		tempt = find(v2);
		tempt = 2;
	}

	for (int i = 1; i <= vertex; i++){
		int value = find(i);
		//cout << "vertex = " << i << "; value = " << value << endl;
		mNeed.insert(value);
	}

	cout << mNeed.size() - 1 << endl;

	return 0;
}

void buildDsu( int size ){
	mParent.resize(size);
	while (size){
		size--;
		mParent[size] = size;
	}
}

int find(int x){
	if (mParent[x] == x){
		return x;
	}
	int f = find(mParent[x]);
	return mParent[x] = f;
}

bool unionDsu(int x , int y){

	x = find(x);
	y = find(y);
	if (x != y){
		mParent[x] = y;	
		return true;
	}

	return false;
}


