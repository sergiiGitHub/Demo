#include <stdio.h>
#include <iostream>

using namespace std;

int rStep, allStep, i, j, *stepArray;

void read(){
    //freopen("..\\input.txt", "r", stdin);
    cin >> rStep >> allStep;
    //fclose(stdin);
}

int main(int argc, char* argv[])
{
    read( );
	if ( rStep == 1 ){
		cout << 1 << endl;
	} else {
    stepArray = new int[ allStep ];
		for ( i = 0; i < allStep; ++i ){
			stepArray[i] = 0;
		}

		for ( i = 0; i < allStep; ++i ){
			if ( i < rStep ){
				stepArray[i] += 1;
			}
			for ( j = i+1; (j < (i + 1 + rStep)) & (j < allStep); ++j ){
				stepArray[j] += stepArray[i];
			}
		}

		//for ( i = 0; i < allStep; ++i ){
		//    cout << stepArray[i] << ", ";
		//}
		cout << stepArray[ allStep - 1 ] << endl;
	}
    return 0;
}

