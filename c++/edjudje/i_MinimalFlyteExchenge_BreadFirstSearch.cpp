// ConsoleApplication2.cpp : Defines the entry point for the console application.
//
/*

Маршрут с минимальным количеством пересадок
Time limit: 1 s 
Memory limit: 64 M 

Сотруднику компании Самсунг необходимо попасть из города А в город В пользуясь любимым воздушным транспортом. К сожалению, не всегда из города А есть прямой рейс в город В, и маршрут может содержать пересадки. Поскольку этот сотрудник знает по опыту, что каждая такая пересадка увеличивает шанс потери багажа, он очень заинтересован, чтобы количество пересадок было минимальным. Необходимо найти оптимальный с точки зрения этого сотрудника маршрут и вывести количество пересадок, которые он содержит. 

Input format
Первая строка содержит четыре числа разделенных пробелами – количество городов N (1 ≤ N ≤ 100), количество рейсов K (1 ≤ K ≤ 5000), которые соединяют эти города, индексы первого и последнего города в маршруте A, В (A != B). В следующей строке идет K пар чисел разделенных пробелами – каждая пара чисел содержит индексы двух городов, которые соединены рейсом в обе стороны (индексы представлены числами от 1 до N включительно). 

Output format
Количество пересадок, которое содержит наиболее оптимальный маршрут или число -1, если проложить маршрут из А в B не возможно. 

Notes
 
Сотруднику необходимо попасть из города 1 в город 6. Маршрут с наименьшим количеством пересадок будет 1 > 3 > 4 > 5 > 6 и, соответственно, количество пересадок будет 3. 

Input 
8 10 1 6
1 3 4 3 2 3 4 5 6 5 7 5 8 5 2 7 7 8 8 6
------------------------------------------
Output 
 3
 

*/


#include <stdio.h>
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int bfs(vector< vector < int > >& graph, vector < int >& races, int& start, int& end);

int main(int argc, char* argv[])
{
	//TODO remove before commit
	//FILE *stream;
	//freopen_s(&stream, "inp.txt", "r", stdin);

	int cityNumber = -1;
	int raceNumber = -1;
	int startCity  = -1;
	int endCity = -1;

	cin >> cityNumber >> raceNumber >> startCity >> endCity;
	vector< vector < int > > graph( cityNumber + 1 );
	vector < int > races(cityNumber + 1, -1);

	int v1, v2;
	while (cin >> v1 >> v2 ){
		graph.at(v1).push_back(v2);
		graph.at(v2).push_back(v1);
	}

	cout << bfs(graph, races, startCity, endCity);

	return 0;
}

int bfs(vector< vector < int > >& graph, vector < int >& races, int& start, int& end){

	vector< bool > visited(graph.size(), false);
	queue< int > queue;

	queue.push(start);
	visited.at(start) = true;

	bool isForceFinish = false; 
	while (!queue.empty() && !isForceFinish){
		int from = queue.front();
		queue.pop();
		for (int index = 0; index < graph.at(from).size(); index++){
			int to = graph.at(from).at(index);
			if (!visited.at(to)){
				visited.at(to) = true;
				races.at(to) = from;
				queue.push( to );
				if (to == end){
					isForceFinish = true;
					break;
				}
			}
		}
	}

	int pointer = end;
	int counter = -1;
	while (races.at(pointer) != -1){
		pointer = races.at(pointer);
		counter++;
	}

	return counter;
}
