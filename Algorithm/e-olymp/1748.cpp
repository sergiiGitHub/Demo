#include <vector>
#include <iostream>

using namespace std;

vector < vector< int > > graph;

int main() {
	//freopen("input.txt", "r", stdin);
	int n , m;
	cin >> n >> m;

	for (int i = 0; i < n; ++i){
		vector< int > v;
		graph.push_back(v);
	}

	int v1, v2;
	for (int i = 0; i < m; ++i){
		
		cin >> v1 >> v2;
		--v1;
		--v2;

		graph.at(v1).push_back(v2);
		graph.at(v2).push_back(v1);
	}
	int k;
	cin >> k;
	vector< int > res;
	for (int i = 0; i < n; ++i){
		if (k <= graph.at(i).size()){
			res.push_back(i);
		}
	}

	cout << res.size() << endl;
	for (int i = 0; i < res.size(); ++i){
		cout << res.at(i) + 1 << " ";
	}
	return 0;
}