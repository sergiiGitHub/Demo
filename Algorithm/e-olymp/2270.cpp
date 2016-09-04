#include <iostream>
#include <vector>
using namespace std;

#define SIZE 100000

int v, r;

vector< vector<int> > graph;
bool visited[100000] = { 0 };

bool dfs( int v ){

	if (visited[v]){
		return true;
	}
	visited[v] = true;
	for (int i = 0; i < graph.at(v).size(); ++i){
		int to = graph.at(v).at(i);
		if (dfs(to)){
			return true;
		}
	}

	visited[v] = false;
	return false;
}

int main()
{
	freopen("input.txt", "r", stdin);

	cin >> v >> r;

	for (int i = 0; i < v; ++i){
		vector<int> v;
		graph.push_back(v);
	}

	int v1, v2;
	for (int i = 0; i < r; ++i){
		cin >> v1 >> v2;
		--v1;
		--v2;
		graph.at(v1).push_back(v2);
	}

	int ans = false;
	for (int i = 0; i < v; ++i){
		if (dfs(i)){
			ans = true;
		}
	}

	if (ans ){
		cout << "YES" << endl;
	} else {
		cout << "NO" << endl;
	}
	return 0;
}