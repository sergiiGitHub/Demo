#include <iostream>
#include <stdio.h>

using namespace std;


struct Point{
    int x;
    int y;
};

int getLineExpretion( Point o, Point a, Point b ){
     return ( o.x - a.x )*( b.y - a.y ) - ( b.x - a.x )*( o.y - a.y );
}

//int getLineExpretion( Point o, Point a, Point b ){
//     return ( b.x - a.x )*( o.y - a.y ) - ( b.y - a.y )*( o.x - a.x );
//}

int main()
{
    freopen ("input.txt", "r", stdin);

    Point o, a, b, c;

    while( cin >> o.x >> o.y) {
        cin >> a.x >> a.y;
        cin >> b.x >> b.y;
        cin >> c.x >> c.y;

        int r1 = getLineExpretion( o, a, b );
        int r2 = getLineExpretion( o, b, c );
        int r3 = getLineExpretion( o, c, a );

        //cout << r1 << " " << r2 << " " << r3 <<  endl;
        bool r =( r1 >= 0 &&  r2 >= 0 && r3 >= 0 ) || ( r1 <= 0 &&  r2 <= 0 && r3 <= 0 );
        cout << (r ? 1 : 0) << endl;
    }

    return 0;
}