/*Go to Settings -> Compiler ->
-> Select "Have g++ follow the C++11 ISO C++ language standard [-std=c++11]"

output
main, foo and bar now execute concurrently...
increase: v: 1
increase: v: 2
increase: v: 3
increase: v: 4
increase: v: 5
decrease: v: 4
decrease: v: 3
decrease: v: 2
decrease: v: 1
decrease: v: 0
foo and bar completed.


*/
#include <iostream>           // std::cout
#include <thread>             // std::thread
#include <chrono>             // sleep
#include <mutex>              // std::mutex, std::unique_lock
#include <condition_variable> // std::condition_variable

using namespace std;

static int value = 0;
mutex mtx;
std::condition_variable cv;
bool ready = false;

void increase()
{
    std::unique_lock<std::mutex> lck(mtx);
    for( int i = 0; i < 5; ++i ){
        ++value;
        cout << "increase: v: " << value << endl;
        this_thread::sleep_for(chrono::milliseconds(4));
    }

    ready = true;
    cv.notify();
}

void decrease()
{
    std::unique_lock<std::mutex> lck(mtx);
    while (!ready) {
        cv.wait(lck);
    }

    for( int i = 0; i < 5; ++i ){
        --value;
        this_thread::sleep_for(chrono::milliseconds(2));
        cout << "decrease: v: " << value <<  endl;
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
