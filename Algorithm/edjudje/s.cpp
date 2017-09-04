//https://89.209.12.198/cgi-bin/new-client?SID=8d5a8f6a635053ff&action=139&prob_id=19

﻿#include <iostream>

using namespace std;

#define SIZE 100000

long long max(long long a, long long b);

int main(void)
{
	//freopen("sample_input.txt", "r", stdin);

	int N;
	long long value;
	long long maxSum = 0;
	long long prev = 0;

	cin >> N;
	cin >> value;
	//value = 1000000;
	prev = value;

	//for (int index = 1; index < SIZE; index++){
	for (int index = 1; index < N; index++){
		cin >> value;
		prev = value + max(0, prev);
		if (prev > maxSum){
			maxSum = prev;
		}
	}

	cout << maxSum << endl;

	return 0;
}


long long max(long long a, long long b){
	if (a > b){
		return a;
	}else {
		return b;
	}
}
