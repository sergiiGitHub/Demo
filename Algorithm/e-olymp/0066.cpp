#include <iostream>
#include <stdio.h>

#include <string>
#include <sstream>

using namespace std;
struct Schedule{
    int start;
    int end;
    static const int hToM = 60;
private:
    int getMinutes( string str ){
        int h, m;
        string toInt;
        std::stringstream stream(str);       
        getline(stream, toInt, ':');
        h = stoi( toInt );
        getline(stream, toInt, ':');
        m = stoi( toInt );
        return h * hToM + m;
    }
public:
    friend ostream& operator<<(ostream& os, const Schedule& sc);

    Schedule( string line ){
        std::stringstream stream(line);
        string string;
        //start
        stream >> string;
        start = getMinutes(string);

        //end
        stream >> string;
        end = getMinutes(string);
    }

    Schedule(){ }
};

ostream& operator<<(ostream& os, const Schedule& sc)
{
    os << sc.start << ' ' << sc.end;
    return os;
}

#define SIZE 1000

int N;
Schedule schedules[SIZE];
Schedule schedulesBuff[SIZE];

inline void justMerge( int left, int mid, int right)
{
    int k = 0, i = left, j = mid + 1;
    while (i <= mid && j <= right)
    {
        if ( schedules[i].end < schedules[j].end ){
            schedulesBuff[k] = schedules[ i ];
            ++i;
        } else {
            schedulesBuff[k] = schedules[ j ];
            ++j;
        }
        ++k;
    }
    while (i <= mid){
        schedulesBuff[k++] = schedules[i++];
    }
    for (int q = 0; q < k; ++q){
        schedules[q + left] = schedulesBuff[q];
    }
}


inline void mergeSort( int left, int right)
{
    if (right - left < 1) return;
    int mid = (left + right) / 2;
    mergeSort( left, mid);
    mergeSort( mid + 1, right);
    justMerge( left, mid, right);
}

int main()
{
    //freopen ("input.txt", "r", stdin);
   
    string input;
    getline(std::cin, input);
    stringstream stream(input);
    stream >> N;
    for ( int i = 0; i < N; ++i ){
        std::getline( std::cin, input );
        Schedule s( input );
        schedules[ i ] = s;
    }

    mergeSort( 0, N - 1 );

    int result = 1;
    Schedule prev = schedules[0];
    for ( int i = 1; i < N; ++i ){
        if ( prev.end <= schedules[i].start ){
            ++result;
            prev = schedules[i];
        }
    }

    cout << result << endl;

    return 0;
}