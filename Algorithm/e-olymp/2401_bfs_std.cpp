#include <iostream>
#include <queue>
using namespace std;

#define SIZE 100

bool table[SIZE][SIZE];
int parent[SIZE] = { 0 };
int visited[SIZE] = { 0 };

int n, s, f;

bool bfs(int v){

	queue< int > q;
	q.push(v);
	visited[v] = true;
	while (!q.empty()){
		int from = q.front();
		q.pop();
		for (int to = 0; to < n; ++to){
			if (table[from][to] && !visited[to]){
				parent[to] = from;
				q.push(to);
				visited[to] = true;
				if (to == f){
					return true;
				}
			}
		}
	}
	return false;
}

int main()
{
	//freopen("input.txt", "r", stdin);

	cin >> n >> s >> f;
	--s;
	--f;
	for (int y = 0; y < n; ++y){
		for (int x = 0; x < n; ++x){
			cin >> table[y][x];
		}
		parent[y] = -1;
	}

	if ( bfs(s) ){
		int size = 0;
		int p = parent[f];
		while (p != -1){
			p = parent[p];
			++size;
		}
		cout << size;
	} else {
		cout << 0;
	}

	return 0;
}