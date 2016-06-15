// aps02.cpp : Defines the entry point for the console application.
//

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <iostream>

using namespace std; 

#define SIZE 8
int arr[SIZE] = { 1, 2, 4, 8, 16, 32, 64, 128 };

int n = 0;

int combination(){
	int size = (1 << SIZE);
	int res = 2147483647;
	for (int i = 1; i < size; ++i){
		int count1 = 0;
		int s1 = 0;
		for (int k = 0; k < SIZE; ++k){
			bool isFirst = (i >> k) & 1;
			if (isFirst){
				s1 += arr[k];
				++count1;
			}
		}
		for (int j = 0; j < size; ++j){
			if (i == j){
				continue;
			}
			int count2 = 0;
			int s2 = 0;
			for (int k = 0; k < SIZE; ++k){
				bool isFirst = (j >> k) & 1;
				if (isFirst){
					s2 += arr[k];
					++count2;
				}
			}
			int diff = s1 - (s2 + n);
			if (diff != 0){
				continue;
			}
			int count = count1 + count2;
			if (count < res){
				res = count;
			}
		}
	}

	return res;
}

int main(int argc, char* argv[])
{
	//freopen("input.txt", "r", stdin);

	while (cin >> n){
		cout << combination() << endl;
	}
	
	return 0;
}