
/*
Problem B-Максимальный приз
Максимальный приз
Time limit: 1 s 
Memory limit: 64 M 

Победителю курса «APS2» в качестве награды предложили денежый приз N грн (10 ≤ N ≤ 999999). Он может увеличить или уменшить его совершив ровно K (0 ≤ K ≤ 109) перестановок цифр в этом числе (переставлять можно две любые цифры в числе). Найдите максимальное значение денежного приза после K перестановок. 

Например при N = 123 и K = 1, после перестановки первой и последней цифры можно получить число 321. Если K = 2, то макимальный приз будет 312. 

Input format
Одна строка, которая содержит два числа: число N (10 ≤ N ≤ 999999) и число перестановок K (0 ≤ K ≤ 109) разделенные пробелом. 

Output format
Максимальное значение денежного приза после K перестановок. 

Notes
Input Output 
32888 2
 88832
 

*/

#include <stdio.h>
#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

void permute(string& aString, int l, int r, int k, string &aMaxValue, int &kAmswer );

int main(int argc, char* argv[])
{
	//FILE *stream;
	//freopen_s(&stream, "inp.txt", "r", stdin);

    string stringNumber = "";
    string stringAmswer = "";
    int kAmswer = -1;
    int k = -1;

    cin >> stringNumber >> k;

    permute(stringNumber, 0, stringNumber.size() - 1, k, stringAmswer, kAmswer );

    if (kAmswer%2){
        swap((stringAmswer[stringAmswer.size()-2]), (stringAmswer[stringAmswer.size()-1]));
    }

    cout << stringAmswer << endl;
    return 0;
}

void permute(string& aString, int l, int r, int k, string &stringAmswer, int &kAmswer )
{
    if (l == r || k == 0){
        //cout << aString << " :: " << k << endl;
        if ( aString > stringAmswer ){
            stringAmswer = aString;
            kAmswer = k;
        }
    } else {
        for (int i = l; i <= r; i++) {
            swap((aString[l]), (aString[i]));
            permute(aString, l+1, r, k-1, stringAmswer, kAmswer);
            swap((aString[l]), (aString[i]));
        }
    }
}
