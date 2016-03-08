// aps3.cpp : Defines the entry point for the console application.
//

/*
Problem E-Серийный номер 1
Серийный номер 1
Time limit: 1 s 
Memory limit: 64 M 

Компания, выпускающая мобильные телефоны, присваивает каждому изделию серийный номер, который представлен целым числом от 0 до 109+7. В связи с невнимательностью одного из сотрудников компании часть выпущенных телефонов оказалась прошита неправильной прошивкой. У вас имеется отсортированный по возрастанию список серийных номеров таких телефонов. Ваша задача из другого списка серийных номеров телефонов отобрать все серийные номера бракованных телефонов, и вывести их количество.

Input format
Первая строка содержит количество серийных номеров бракованных телефонов N (1≤N≤1000000). Во второй строке следует N отсортированных серийных номеров бракованных телефонов, разделенные пробелом. Третья строка содержит количество серийных номеров, которые нужно проверить K (1≤K≤1000000). В четвертой строке следует K серийных номеров, которые необходимо проверить, разделенные пробелом.

Output format
Число серийных номеров бракованных телефонов во втором списке.

Notes
Input 
2
183099332 183099333
2
183099331 183099332

Output 
 1
 

*/

#include "stdio.h"
#include "iostream"

using namespace std;

bool bs( long *space, long size, long value );

int main(int argc, char* argv[])
{
	int index = 0;
	long numberIncorrect;
	long numberCorrect;
	long *incorrectArray, *correctArray;

	cin >> numberIncorrect;
	incorrectArray = new long[numberIncorrect];

	while (index < numberIncorrect){
		cin >> incorrectArray[index];
		index++;
	}

	cin >> numberCorrect;
	correctArray = new long[numberCorrect];
	index = 0;
	while (index < numberCorrect){
		cin >> correctArray[index];
		index++;
	}

	long result = 0;
	for (index = 0; index < numberCorrect; index++){
		if (bs(incorrectArray, numberIncorrect, correctArray[index])){
			result++;
		}
	}

	cout << result;

	delete[] correctArray;
	delete[] incorrectArray;

	return 0;                                                                              
}

bool bs(long *space, long size, long value){
	long left = 0;
	long right = size - 1;

	while (left <= right){
		long mid = (right + left) / 2;
		
		if (space[mid] == value){
			return true;
		} else if (space[mid] > value){
			right = mid-1;
		}else {
			left = mid+1;
		}	
	}
	return false;

}