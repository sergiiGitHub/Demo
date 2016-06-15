// ConsoleApplication6.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>
#include <string>

using namespace std;

int main(int argc, char* argv[])
{
	//FILE *stream;
	//freopen_s(&stream, "inp.txt", "r", stdin);

	string str;
	cin >> str;
	//cout << str << endl;
	long C = 1009, D = 1000000007;

	long hash = 0;
	long prevC = 1;

	hash = str[0];
	for (int i = 1; i < str.size(); i++){
		prevC *= C;
		prevC %= D;
		hash += ((long)(str[i] * prevC) % D);
		hash %= D;
	}
	//cout << "finish"<< endl;
	cout << hash << endl;
	return 0;
}

