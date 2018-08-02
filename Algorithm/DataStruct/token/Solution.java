//import java.io.File;
//import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner;

//        try {
//            scanner = new Scanner(new File("input.txt"));
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return;
//        }
        scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        for (int test = 1; test <= tests; ++test)
        {
            seed = scanner.nextLong();
            str_len = scanner.nextInt();
            xnum = scanner.nextInt();
            int correct = scanner.nextInt();

            s.setLength(0);
            gen(0);

            int ans = UserSolution.polynome(s.toString(), xnum);
            int score = (ans == correct) ? 100 : 0;
            System.out.println("#" + test + " " + score);
        }
    }

    static long seed;
    static int mrand(long num)
    {
        seed = seed * 1103515245 + 37209;
        return (int)((seed >>> 8) % num);
    }

    final static  int MAX_SLEN = 10000000;
    static int xnum;
    static int str_len;
    static StringBuilder s = new StringBuilder(MAX_SLEN);

    static void gen(int lvl)
    {
        boolean left_bracket;
        boolean right_bracket = false;

        while (s.length() < (str_len - 16))
        {
            int a = mrand(999) + 1;
            int x = mrand(xnum);
            boolean plus = mrand(2) > 0;
            left_bracket = (mrand(8) == 0);

            if (right_bracket && (lvl != 0))
                break;
            else if (left_bracket && (lvl < 5))
            {
                a = mrand(99) + 1;
                s.append(a).append('(');
                gen(lvl + 1);
                s.append(")").append(plus ? '+' : '-');
            }
            else
            {
                s.append(a).append('x').append(x).append(plus ? '+' : '-');
            }

            right_bracket = (mrand(8) == 0);
        }
        char c = s.charAt(s.length() - 1);
        if (c == '+' || c == '-') s.deleteCharAt(s.length() - 1);
    }

}
