// ConsoleApplication3.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>
#include <vector>

using namespace std;


int numberV;
vector< vector< int > > graph;
vector< int > amount;
//------------------
vector< int > first;
vector< int > second;

void read(){
	//freopen("input.txt", "r", stdin);

	cin >> numberV;
	int val;
	for (int i = 0; i < numberV; ++i){
		cin >> val;
		amount.push_back(val);
		vector< int > v;
		graph.push_back(v);
		first.push_back(0);
		second.push_back(0);
	}

	for (int i = 0; i < numberV - 1; ++i){
		cin >> val;
		--val;
		graph.at(val).push_back(i+1);
	}
}

void print(){
	for (int i = 0; i < numberV; ++i){
		cout << "v:" << i + 1 << "; c :";
		for (int j = 0; j < graph.at(i).size(); ++j){
			cout << graph.at(i).at(j) + 1 << " ";
		}
		cout << endl;
	}

	cout << "first" << endl;
	for (int i = 0; i < numberV; ++i){
		cout << first[i] << " ";
	}
	cout << endl;
	cout << "second" << endl;
	for (int i = 0; i < numberV; ++i){
		cout << second[i] << " ";
	}
	cout << endl;
}

void dfs( int v ){
	vector< int > vertex = graph.at(v);
	for (int i = 0; i < vertex.size(); ++i){
		dfs(vertex.at(i));
	}
	if (vertex.empty()){
		first.at(v) = amount.at(v);
		second.at(v) = 0;
	} else {
		first.at(v) = amount.at(v);
		for (int i = 0; i < vertex.size(); ++i){
			int val1 = second.at(vertex.at(i));
			int val2 = first.at(vertex.at(i));

			first.at(v) += val1;
			if (val2 > val1){
				second.at(v) += val2;
			} else {
				second.at(v) += val1;
			}
		}
	}
}

int main(int argc, char* argv[])
{
	read();
	//print();
	dfs( 0 );
	//print();

	if (first[0] > second[0]){
		cout << first[0] << endl;
	} else {
		cout << second[0] << endl;
	}

	return 0;
}

