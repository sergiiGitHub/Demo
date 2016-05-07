// smth.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>
#include <algorithm>

using namespace std;

struct MaxHeap{

	int *mHeap;
	int mSize;

	MaxHeap( int capacity );
	~MaxHeap();

	bool empty();
	void insert(int x);
	int max( ) const;
	int pop();
};

int main(int argc, char* argv[]){

	const int size = 5;
	int elements[size] = { 1, 0, 7, 3, 4 };
	MaxHeap mMaxHeap(size);

	int index = 0;
	while( index < size ){
		mMaxHeap.insert( elements[index] );
		index++;
	}

	index = 3;
	while(index--){
		cout << mMaxHeap.pop() << " ";
		index;
	}
	return 0;
}

int MaxHeap::pop(){
	int pointer = 0;
	const int result = mHeap[pointer];
	swap( mHeap[pointer], mHeap[--mSize] );
	
	while(true){
		int chield1 = pointer*2 + 1;
		int chield2 = pointer*2 + 2;
		int sc = pointer;
		if ( chield1 < mSize && mHeap[chield1] > mHeap[sc] ){
			sc = chield1;
		}
		if ( chield2 < mSize && mHeap[chield2] > mHeap[sc] ){
			sc = chield2;
		}
		if ( sc == pointer){
			break;
		}

		swap( mHeap[sc], mHeap[pointer] );
		pointer = sc;
	}
	return result;
}

MaxHeap::MaxHeap(int aCapacity){
	mHeap = new int[aCapacity];
	mSize = 0;
}

bool MaxHeap::empty(){
	return mSize == 0;
}

int MaxHeap::max() const{
	return mHeap[0];
}

void MaxHeap::insert( int x ){
	int pointer = mSize++;
	mHeap[pointer] = x;

	int parent = ( pointer - 1 ) / 2;
	while( parent >= 0 && mHeap[ parent ] < mHeap[ pointer ] ){
		swap( mHeap[ parent ], mHeap[ pointer ] );
		pointer = parent;
		parent = ( pointer - 1 ) / 2;
	}
}

MaxHeap::~MaxHeap(){
	delete [ ] mHeap;
}
