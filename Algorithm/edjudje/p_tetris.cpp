#include <iostream>
#include <stdio.h>

#include <vector>

using namespace std;

/*
 * 
 * input
 * 6 10
 * 1 2 5 7 2 2 2 8 0 9 6 6
 * output
 * 5
 * 
 * |          |8
|          |7
|          |6
|      #   |5
|##########|4
|  ####### |3
|  #       |2
| ##  ###  |1
|0123456789|0
 */

struct Point {
    int s;
    int e;

    Point( int aS, int aE ) : s(aS), e(aE) { }

    bool isLie( Point& other ){
        return (other.s <= e && other.s >= s) || (other.e >= s && other.e <= e)
            || (s <= other.e && s >= other.s) || (e >= other.s && e <= other.e);
    }
};

vector< vector< Point > > table;

bool isLie( vector< Point > level, Point aPoint  ){
    for ( int i = 0; i < level.size(); ++i ){
        if ( level.at(i).isLie(aPoint) ){
            return true;
        }
    }
    return false;
}

int main()
{
    int w;
    int n;
    //freopen ("input.txt", "r", stdin);
   
    cin >> n >> w;

    int v1, v2;

    for ( int i = 0; i < n; ++i ){
        cin >> v1 >> v2;
        Point point( v1, v2 );
        if ( table.empty() ){
            vector<Point> height;
            height.push_back(point);
            table.push_back( height );
        } else {
            for ( int h = table.size() - 1; h >= 0; --h){
                vector< Point > hieght = table.at( h );
                if ( isLie( hieght, point ) ){
                    if ( h == table.size() - 1){
                        vector<Point> height;
                        height.push_back(point);
                        table.push_back( height );
                    } else {
                        table.at( h+1 ).push_back( point );
                    }
                    break;
                }
            }
        }
    }

    cout << table.size() << endl;
    return 0;
} 
  