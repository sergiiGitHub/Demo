// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
import java.util.LinkedList;
import java.util.List;
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED

/*
 * Need to display word where char meet two times
 * @input 
 * "democracy", 5
 * @output
 * [ocrac, cracy]
 */

public class Solution2 {
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	public List<String> subStringsLessKDist(String inputString, int num) {
		List<String> res = new LinkedList<>();

		if (inputString.length() < num) {
			return res;
		}
		int two = 0;
		int meetChar[] = new int[26];
		int k = 0;
		for (; k < num; ++k) {
			int ci = inputString.charAt(k) - 'a';
			++meetChar[ci];
			if (meetChar[ci] == 2) {
				++two;
			}
		}
		if (two == 1) {
			res.add(inputString.substring(0, k));
		}
		for (int i = k; i < inputString.length(); ++i) {
			int last = i - num;
			int ci = inputString.charAt(last) - 'a';
			int was = meetChar[ci];
			--meetChar[ci];
			if (was == 2 && meetChar[ci] == 1) {
				--two;
			}
			// -------------------
			ci = inputString.charAt(i) - 'a';
			++meetChar[ci];
			if (meetChar[ci] == 2) {
				++two;
			}
			if (two == 1) {
				res.add(inputString.substring(last + 1, i + 1));
			}
		}

		return res;
	}
	// METHOD SIGNATURE ENDS
}
