
/*
Task taken from http://practice.geeksforgeeks.org/problems/job-sequencing-problem/0#ExpectOP

 2
 4
 1 4 20 2 1 10 3 1 40 4 1 30
 5
 1 2 100 2 1 19 3 2 27 4 1 25 5 1 15

 =>
 2 60
 2 127
  */
#include <iostream>
#include <cstdlib>
using namespace std;

#define MAX_JOBS 100

static int parent[MAX_JOBS+1];

// A structure to represent a job
struct Job
{
    int id;     // Job Id
    int deadLine;    // Deadline of job
    int profit;  // Profit if job is over before or on deadline
};

int max(int a, int b) {
    return a > b? a : b;
}

void mysort(Job arr[], int left, int right) {
    int i = left, j = right;
    int pivot = arr[(left + right) / 2].profit;
    Job tmp;

    while (i <= j) {
        while (arr[i].profit > pivot) {
            i++;
        }
        
        while (arr[j].profit < pivot) {
            j--;
        }
        
        if (i <= j) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    };
    
    if (left < j) {
        mysort(arr, left, j);
    }
    
    if (i < right) {
        mysort(arr, i, right);
    }
}

// A Simple Disjoint Set Data Structure
struct DisjointSet
{
 
    // Constructor
    DisjointSet(int n)
    {
        // Every node is a parent of itself
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
		}
    }
 
    // Path Compression
    int find(int s)
    {
        /* Make the parent of the nodes in the path
           from u--> parent[u] point to parent[u] */
        if (s == parent[s]) {
            return s;
        }
        return parent[s] = find(parent[s]);
    }
 
    // Makes u as parent of v.
    void merge(int u, int v)
    {
        //update the greatest available
        //free slot to u
        parent[v] = u;
    }
};

// Functions returns the maximum deadline from the set
// of jobs
int findMaxDeadline(struct Job arr[], int n)
{
    int ans = 0;
    for (int i = 0; i < n; i++) {
        ans = max(ans, arr[i].deadLine);
    }
    return ans;
}

// Returns minimum number of platforms required
void printJobScheduling(Job arr[], int n)
{
    // Sort all jobs according to decreasing order of profit
    mysort(arr, 0, n - 1);

    // Find the maximum deadline among all jobs and
    // create a disjoint set data structure with
    // maxDeadline disjoint sets initially.
    int maxDeadline = findMaxDeadline(arr, n);
    DisjointSet ds(maxDeadline);
    int totalJobs = 0, totalProfit = 0;
    // Traverse through all the jobs
    for (int i = 0; i < n; i++)
    {
        // Find the maximum available free slot for
        // this job (corresponding to its deadline)
        int availableSlot = ds.find(arr[i].deadLine);
 
        // If maximum available free slot is greater
        // than 0, then free slot available
        if (availableSlot > 0)
        {
            // This slot is taken by this job 'i'
            // so we need to update the greatest 
            // free slot. Note that, in merge, we 
            // make first parameter as parent of 
            // second parameter. So future queries
            // for availableSlot will return maximum
            // available slot in set of 
            // "availableSlot - 1"
            ds.merge(ds.find(availableSlot - 1), 
                             availableSlot);
 
            totalProfit += arr[i].profit;
	    totalJobs++;
        }
    }
    cout << totalJobs << " " << totalProfit << endl;
}

static int T, N;
static Job mAllJobs[MAX_JOBS+1];

int randw(int mod) {
  return rand() % mod;
}

int main(int argc, const char * argv[]) {
    std::cin >> T;
#if 1	
    // solution
    while (T-- > 0) {
        std::cin >> N;
        N = N <= MAX_JOBS? N: MAX_JOBS;
        int index = 0;
        while(index < N) {
            Job& ref = mAllJobs[index++];
            std::cin >> ref.id;
            std::cin >> ref.deadLine;
            std::cin >> ref.profit;
        }
        printJobScheduling(mAllJobs, N);
    }
#else
    // test gen	
    srand(time(NULL));
    cout << T << endl;
    while (T-- > 0) {
        int index = 0;
        //cout << "have " << T << " " << N << endl;
        N = randw(100);
        std::cout << N << endl;
        while(index++ < N) {
            std::cout << " " << index << " " << randw(100) << " " << randw(500);
        }
        std::cout << endl;
    }
#endit	
    return 0;
}
