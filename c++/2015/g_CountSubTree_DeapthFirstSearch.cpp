// aps02.cpp : Defines the entry point for the console application.
//

/*
Размеры всех поддеревьев
Time limit: 1 s 
Memory limit: 64 M 

Ваша задача для заданного дерева найти размеры всех его поддеревьев и вывести эти размеры. Размер поддерева, это количество вершин, которые составляют данное поддерево. 

Input format
Первая строка содержит одно число N – количество вершин в дереве (1 ≤ N ≤ 100000). В следующей строке идет (N-1) пар чисел описывающих ребра дерева – пара чисел содержит индексы вершин, которые соединены ребром (индексы представлены числами от 1 до N включительно). 

Output format
N чисел, где i-тое число соответствует размеру поддерева, которое начинается в вершине с индексом i. 

Notes
 Input  
13
1 2 1 3 2 4 3 5 3 6 4 7 7 12 5 9 5 8 6 10 6 11 11 13
Output
 13 4 8 3 3 4 2 1 1 1 2 1 1
 

Размер поддерева, начинающегося в вершине 3 – 8.
Размер поддерева, начинающегося в вершине 4 – 3.
Размер поддерева, начинающегося в вершине 13 – 1.

*/

#include <stdio.h>
#include <iostream>
#include <vector>

using namespace std;

int dfs_sum(const vector< vector< int > > &graph, const int& index);

int main(int argc, char* argv[])
{
//	FILE *stream;
//	freopen_s(&stream, "inp.txt", "r", stdin);

	int v1, v2;
	int graphSize = 0;
	cin >> graphSize;
	vector< vector< int > > graph;
	for (int i = 0; i < graphSize+1; ++i){
		vector<int> v;
		graph.push_back(v);
	}

	for (int i = 0; i < graphSize-1; ++i){
		cin >> v1 >> v2;
		graph.at(v1).push_back(v2);
	}

	for (int i = 1; i < graphSize+1; ++i){ 
		cout << dfs_sum(graph, i) << " ";
	}

	return 0;
}

int dfs_sum(const vector< vector< int > > &graph, const int& index){

	int sum = 1;
	for (int i = 0; i < graph.at(index).size(); i++){
		int to = graph.at(index).at(i);
		sum += dfs_sum(graph, to);
	}
	return sum;
}


