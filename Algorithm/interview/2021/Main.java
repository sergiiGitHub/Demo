
/**
 *

A string is called balanced when every letter occurring in the string, appears both in upper- and lowercase.
        For example, the string "CATattac" is balanced ('a', 'c' and 't' occur in both cases),
        but "Madam" is not ('d' and 'a' appear only in lowercase).
        Note that the number of occurrences does not matter.

        Write a function:

class Solution { public int solution(String S); }

that, given a string S of length N, returns the length of the shortest balanced fragment of S. If S does not contain any balanced fragments, the function should return -1.

        Examples:

        1. Given S = "azABaabza", the function should return 5. The shortest balanced fragment of S is "ABaab".

        2. Given S = "TacoCat", the function should return -1. There is no balanced fragment.

        3. Given S = "AcZCbaBz", the function should return 8. The shortest balanced fragment is the whole string.

        4. Given S = "abcdefghijklmnopqrstuvwxyz", the function should return -1.

        Assume that:

        N is an integer within the range [1..200];
        string S consists only of letters ('a'−'z' and/or 'A'−'Z').

        In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 */
public class Main {

    public static void main(String[] args) {

        ISolution solution = new Solution();
        //solution2.solution("BaaabA"); //
        check(solution, "Aabbb");
        check(solution, "ABaab");
        check(solution, "TacoCat");
        check(solution, "AcZCbaBz");
        check(solution, "abcdefghijklmnopqrstuvwxyz");
        check(solution, "abcdefghijklmnopqrstuvwxyzZ");
        check(solution, "CATattac");
        check(solution, "Madam");

    }

    private static void check(ISolution solution, String string) {
        System.out.println("check: s: " + string + " =  " + solution.solution(string));

    }

    public interface ISolution {
        int solution(String s);
    }

    public static class Solution implements ISolution {

        private static final int UNDEFINED = -1;

        @Override
        public int solution(String s) {
            int result = UNDEFINED;
            for (int i = 0; i < s.length() - 1; ++i) {
                int tempLength = check(i, s.length() - 1, s);
                if (tempLength == UNDEFINED) {
                    continue;
                }
                if (result == UNDEFINED || tempLength < result) {
                    result = tempLength;
                }
            }
            return result;
        }

        private int check(int minIndex, int maxIndex, String s) {
            char ch = s.charAt(minIndex);
            int value = getValue(ch);
            boolean islowerLetter = isLowerLetter(ch);
            int checkSumLower = 0;
            int checkSumUpper = 0;
            if (islowerLetter) {
                checkSumLower += value;
            } else {
                checkSumUpper += value;
            }

            int nextIndex = minIndex + 1;
            while((checkSumLower ^ checkSumUpper) != 0 && nextIndex <= maxIndex) {
                ch = s.charAt(nextIndex);
                islowerLetter = isLowerLetter(ch);
                value = getValue(ch);
                if (islowerLetter) {
                    checkSumLower |= value;
                } else {
                    checkSumUpper |= value;
                }
                ++nextIndex;
            }
            return (checkSumLower ^ checkSumUpper) == 0 ? nextIndex - minIndex : UNDEFINED;
        }

        private int getValue(char ch) {
            int shift = getCharIndex(ch);
            return (1 << shift);
        }

        private int getCharIndex(char ch) {
            return isLowerLetter(ch) ? ch - 'a' : ch - 'A';
        }

        private boolean isLowerLetter(char ch) {
            return ('a' <= ch && ch <= 'z');
        }
    }


}
