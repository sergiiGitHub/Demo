#include <stdio.h>
#include <iostream>
#include <queue>

#define MAX_SIZE 34

using namespace std;

bool graph[MAX_SIZE][MAX_SIZE];
bool visited[MAX_SIZE][MAX_SIZE];
int currentSize = -1;
char avilible = '.';
queue<int> q;

const int deltaSize = 4;
int deltaY[] = { 0, 0, 1, -1 };
int deltaX[] = { 1, -1, 0, 0, };
int y, x, perimeter;

void read(){
	freopen("input.txt", "r", stdin);

	cin >> currentSize;
	char val;
	for (y = 0; y < currentSize; ++y){
		for (x = 0; x < currentSize; ++x){
			cin >> val;
			graph[y][x] = val == avilible;
			visited[y][x] = false;
			
		}
	}
	fclose(stdin);
}

void print(){
	cout << currentSize << endl;
	for (y = 0; y < currentSize; ++y){
		for (x = 0; x < currentSize; ++x){
			cout << graph[y][x];
		}
		cout << endl;
	}
}

void bfs(int start){
	
	if (visited[start / currentSize][start % currentSize]){
		return;
	}
	q.push(start);
	visited[start / currentSize][start % currentSize] = true;
	int currentFromX, currentFromY, from, newX, newY, i, newIndex;
	while (!q.empty()){
		from = q.front();
		q.pop();
		currentFromX = from % currentSize;
		currentFromY = from / currentSize;
		//cout << "from y:" << currentFromY << ", x:" << currentFromX << endl;
		for (i = 0; i < deltaSize; ++i){
			newX = currentFromX + deltaX[i];
			newY = currentFromY + deltaY[i];

			if ((0 <= newX && newX < currentSize) && (0 <= newY && newY < currentSize) ){
				if( !visited[newY][newX] ){
					if (graph[newY][newX]){
						q.push(newY * currentSize + newX);
						visited[newY][newX] = true;
					} else {
						++perimeter;
					}
				}
			} else {
				++perimeter;
			}
		}
	}
}

int main(int argc, char* argv[])
{
	read();
	//print();
	perimeter = 0;
	bfs(0);
	bfs((currentSize - 1) * currentSize + currentSize -1);

	//cout << (perimeter) << endl;
	cout << (perimeter-4) * 9 << endl;


	return 0;
}