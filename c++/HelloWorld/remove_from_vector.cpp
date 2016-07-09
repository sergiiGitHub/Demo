#include <iostream>
#include <vector>
// for turn on use flag -std=c++11
#if __cplusplus > 199711L
#define CPLUSPLUS11
#endif
#ifdef CPLUSPLUS11
#include <algorithm>
#include <iterator>
#endif


using namespace std;

int main(){

	vector<int> v;

	for ( int i = 0; i< 10; ++i ){
		v.push_back(i);
	}

	vector<int>::iterator i = v.begin();
	while( i != v.end() ){
		if( *i == 3 ){
			i = v.erase(i); 
		} else {
			++i;	
		}
	}
	i = v.begin();
	while( i != v.end() ){
		cout << *i << " ";
		++i;
	}
	cout << endl;
	
	cout << __cplusplus <<  endl;
        //new c++11
#ifdef CPLUSPLUS11
	v.erase(remove_if(v.begin(), v.end(), [](int n) { return n == 5; }), v.end());
     	copy(v.begin(), v.end(), ostream_iterator<int>(cout, " "));
	cout << endl;
#endif
	return 0;
}


