import java.util.Arrays;
import java.util.Scanner;

/*
 Task taken from http://practice.geeksforgeeks.org/problems/job-sequencing-problem/0#ExpectOP

 1
 4
 1 4 20 2 1 10 3 1 40 4 1 30
 5
 1 2 100 2 1 19 3 2 27 4 1 25 5 1 15

 =>
 2 60
 2 127
 */
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		while (0 < t) {
			--t;
			int n = s.nextInt();
			Job jobs[] = new Job[n];
			int maxDeadLine = 0;
			for (int i = 0; i < n; ++i) {
				jobs[i] = new Job();
				jobs[i].id = s.nextInt();
				jobs[i].dl = s.nextInt();
				jobs[i].p = s.nextInt();
				if (maxDeadLine < jobs[i].dl) {
					maxDeadLine = jobs[i].dl;
				}
			}

			Arrays.sort(jobs);

			int totalJobs = 0, totalProfit = 0;
			Dsu dsu = new Dsu(maxDeadLine + 1);
			// System.out.println(dsu);
			for (int i = 0; i < jobs.length; ++i) {
				// System.out.println(j);
				Job j = jobs[i];

				int slot = dsu.find(j.dl);
				// System.out.println("#" + i + ";" + j + "; slot:" + slot);
				if (slot > 0) {
					dsu.merge(dsu.find(slot - 1), slot);
					totalProfit += j.p;
					++totalJobs;
					// System.out.println(dsu);
				}
			}
			System.out.println(totalJobs + " " + totalProfit);
		}
	}

	static class Dsu {
		int parent[];

		Dsu(int capacity) {
			parent = new int[capacity];
			for (int i = 0; i < capacity; i++) {
				parent[i] = i;
			}
		}

		int find(int child) {
			if (parent[child] == child) {
				return child;
			}
			return parent[child] = find(parent[child]);
		}

		void merge(int p, int c) {
			parent[c] = p;
		}

		@Override
		public String toString() {
			String s = "";
			for (int p : parent) {
				s = s + p + " ";
			}
			return s;
		}
	}

	static class Job implements Comparable<Job> {
		int id;
		int dl;
		int p;

		@Override
		public int compareTo(Job o) {
			return o.p - p;
		}

		@Override
		public String toString() {
			return "id: " + id + "; deadline" + dl + "; profit" + p;
		}
	}
}
