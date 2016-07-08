/*Go to Settings -> Compiler ->
-> Select "Have g++ follow the C++11 ISO C++ language standard [-std=c++11]"

output

main, foo and bar now execute concurrently...
increase: v: 1; t:1
decrease: v: 0; t:0
increase: v: 0; t:0
decrease: v: 0; t:-1  - !!!
decrease: v: -1; t:-1
increase: v: -1; t:-1
decrease: v: -1; t:-2 - !!!
decrease: v: -2; t:-2
increase: v: -1; t:-1
increase: v: 0; t:0
foo and bar completed.

*/
#include <iostream>       // std::cout
#include <thread>         // std::thread
#include <chrono>
#include <mutex>

using namespace std;

static int value = 0;
mutex m;

void increase()
{
    for( int i = 0; i < 5; ++i ){
        m.lock();
        ++value;
        int temp = value;
        m.unlock();
        cout << "increase: v: " << value << "; t:" << temp <<  endl;
        this_thread::sleep_for(chrono::milliseconds(4));
    }
}

void decrease()
{
    for( int i = 0; i < 5; ++i ){
        m.lock();
        --value;
        int temp = value;
        m.unlock();
        this_thread::sleep_for(chrono::milliseconds(2));
        cout << "decrease: v: " << value << "; t:" << temp <<  endl;
    }
}

int main()
{
  thread first (increase);     // spawn new thread that calls foo()
  thread second (decrease);  // spawn new thread that calls bar(0)

  cout << "main, foo and bar now execute concurrently...\n";

  // synchronize threads:
  first.join();                // pauses until first finishes
  second.join();               // pauses until second finishes

  cout << "foo and bar completed.\n";

  return 0;
}
