// aps01.cpp : Defines the entry point for the console application.
/*
Камешки
Time limit: 1 s 
Memory limit: 64 M 

У вас есть N (10 ≤ N ≤ 100) камешков. Вам нужно разделить их на P (P ≤ N) кучек чтобы произведение чисел, которые соответстуют количеству камешков в каждой кучке, было максимальным. 

Например при N = 10 и P = 3 можно разделить камешки так 10 = 1 + 1 + 8. В этом случае произведение будет 1 * 1 * 8 = 8. Оптимальным же будет разделение 10 = 3 + 3 + 4. При этом произведение будет 3* 3* 4 = 36. 

Input format
Одна строка, которая содержит два числа: число камешков N (10 ≤ N ≤ 100) и число кучек P (P ≤ N) разделенные пробелом. 

Output format
Произведение чисел, которые соответстуют количеству камешков в каждой кучке. 

Notes
Input Output 
10 3
 36
 

*/


//

#include <stdio.h>
#include <iostream>

using namespace std;

long  solution(long & stoneNumber, long & poolNumber);

int main(int argc, char* argv)
{
	long stounNumber = 0;
	long poolNumber = 0;
	
	while (cin >> stounNumber >> poolNumber);
	cout << solution(stounNumber, poolNumber) << endl;

	return 0;
}
long  solution(long & stoneNumber, long & poolNumber){
	long result = 1;
	
	const long numberInPool = (stoneNumber) / (poolNumber);

	long rest = (stoneNumber)-(numberInPool*poolNumber);
	for (long poolCounter = 0; poolCounter < poolNumber; poolCounter++){
		if (rest > 0){
			result *= (numberInPool + 1);
			rest--;
		} else {
			result *= numberInPool;
		}
	}

	return result;
}

//unsigned int stoneCount = 0;
//for (unsigned int poolCounter = 0; poolCounter < poolNumber; poolCounter++){
//	unsigned int delta = (stoneNumber - stoneCount) / (poolNumber - poolCounter);
//	result *= delta;
//	stoneCount += delta;
//}

//int result = 0;
//int midle = stoneNumber / poolNumber + 1;
//for (int i = 1; i < midle; i++){
//	int tempresult = 1;
//	int stoneCount = 0;
//	for (int poolCounter = 0; poolCounter < poolNumber - 1; poolCounter++){
//		tempresult *= i;
//		stoneCount += i;
//	}
//	int rest = stoneNumber - stoneCount;
//	if (rest != 0){
//		tempresult *= rest;
//	}
//	if (result < tempresult){
//		result = tempresult;
//	}
//}
//return result;

////cout << "stounNumber = " << stounNumber << "; poolNumber = " << poolNumber << endl;
//
//int delta = stoneNumber / poolNumber;
////cout << "delta = " << delta << endl;
//
//int result = delta;
//int stounCount = delta;
//for (int poolCount = 1; poolCount < poolNumber - 1; poolCount++, stounCount += delta){
//	result *= delta;
//}
//if (stounCount < stoneNumber){
//	int temp = stoneNumber - stounCount;
//	result *= (temp);
//}
//return result;


