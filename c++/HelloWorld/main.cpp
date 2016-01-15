#include <iostream>

using namespace std;

int xor1( int a, int b );

int main()
{
    char str[] = { 'a', 'a', 'b', 'c', 'c', '\0' };

    return 0;
}

int xor1( int a, int b ){
    return ( a | b ) & !(a & b);
}
