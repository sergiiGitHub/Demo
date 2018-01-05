import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {

	/*
	 * Example of use BufferedReader and StreamTokenizer which significantly
	 * speed up read from input In this task
	 * https://acmp.ru/index.asp?main=task&id_task=41 Not pass 11 task case(TLE
	 * > 2s) with BufferedReader and StreamTokenizer the longest take ~4ms
	 */
	public static void main(String[] args) throws IOException {
		StreamTokenizer s = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(System.in)));
		PrintWriter p = new PrintWriter(System.out, true);
		int a[] = new int[201];
		int v, vi, i, n;
		s.nextToken();
		n = (int) s.nval;
		for (i = 0; i < n; i++) {
			s.nextToken();
			v = (int) s.nval;
			v += 100;
			++a[v];
		}
		for (v = -100; v <= 100; v++) {
			vi = v + 100;
			while (0 < a[vi]) {
				p.print(v);
				p.print(' ');
				--a[vi];
			}
		}
		p.flush();
	}
}