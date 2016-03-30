#include <cstdio>
#include <iostream>

using namespace std;

int height, width, size;
int *inputMap, *sumMap;
int *path;

void read(){
    //freopen ( "..//input.txt", "r", stdin);
    cin >> height >> width;

    size = height*width;
    inputMap = new int[ size ];
    sumMap = new int[ size ];
    path = new int[ size ];

    for ( int i = 0; i < size; ++i){
        cin >> inputMap[i];
        path[ i ] = -1;
    }

    //fclose( stdin );
}

void destroy(){
    delete [] inputMap;
    delete [] sumMap;
    delete [] path;
}

void print(){
    int index = 0;
    cout << "input:" << endl; 
    for ( int y = 0; y < height; ++y){
        for ( int x = 0; x < width; ++x){
            cout << inputMap[index] << " ";
            ++index;
        }
        cout << endl;
    }
    cout << "sum:" << endl; 
    index = 0;
    for ( int y = 0; y < height; ++y){
        for ( int x = 0; x < width; ++x){
            cout << sumMap[index] << " ";
            ++index;
        }
        cout << endl;
    }
}

void solution(){
    int newVal;
    int index = 0;
    
    sumMap[ index ] = inputMap[ index ];
    for ( int y = 0; y < height; ++y){
        for ( int x = 0; x < width; ++x){
            if ( x > 0 ){
                newVal = inputMap[ index ] + sumMap[ index - 1 ];
                if ( newVal > sumMap[ index ] ){
                    sumMap[ index ] = newVal;
                    path[ index ] = index -1;
                }
            }
            if ( y > 0 ){
                newVal = inputMap[ index ] + sumMap[ index - width ];
                if ( newVal > sumMap[ index ] ){
                    sumMap[ index ] = newVal;
                    path[ index ] = index - width;
                }
            }
            ++index;
        }
    }
    cout << endl;
    index = size - 1;
    while( path[ index ] != -1 ){
        cout << (( index - path[ index ] > 1 ) ? 'F' : 'R');
        index = path[ index ];
    }
    cout << endl;
}

int main()
{
    read();
    solution();
    //print();
    destroy();
	return 0;

