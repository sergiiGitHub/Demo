#include <iostream>

#define SIZE 10
#define SIZE_DFS 4
#define _CRT_SECURE_NO_WARNINGS

bool table[SIZE][SIZE];

int dx[ SIZE_DFS ] = { 1, -1, 0, 0 };
int dy[ SIZE_DFS ] = { 0, 0, 1, -1 };

using namespace std;

void dfs(int x, int y, int &ans){
	if ( table[y][x] ){
		return;
	}
	++ans;
	table[y][x] = true;
	for (int i = 0; i < SIZE_DFS; ++i){
		int newX = x + dx[i];
        int newY = y + dy[i];
        dfs(newX, newY, ans);
	}
}

int main(){
    //freopen ( "input.txt" , "r", stdin );
	int ans = 0, n;
	cin >> n;
	char v;
	for (int y = 0; y < n; ++y){
		for (int x = 0; x < n; ++x){
			cin >> v;
			table[y][x] = (v == '*');
		}
	}

	int x, y;
	cin >> y >> x;
	--x;
	--y;
	dfs(x, y, ans);
	cout << ans;

	return(0);
}
