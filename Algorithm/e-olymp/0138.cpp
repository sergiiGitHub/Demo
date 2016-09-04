#include <iostream>

using namespace std;

#define SIZE 6
int arr[SIZE] = { 500, 200, 100, 50, 20, 10 };

int main() {
	
	int in = 0, out = 0;
	cin >> in;

	for (int i = 0; i < SIZE; ++i){
		int increment = in / arr[i];
		in -= increment * arr[i];
		out += increment;

		if (in == 0){
			break;
		}
	}
	if (in != 0){
		out = -1;
	}

	cout << out;

	return 0;
}