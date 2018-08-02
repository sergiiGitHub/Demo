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
        int sn_max_len, nadd, npres, correct;

        for (int test = 1; test <= tests; ++test)
        {
            seed = scanner.nextInt();
            sn_max_len = scanner.nextInt();
            nadd = scanner.nextInt();
            npres = scanner.nextInt();
            correct = scanner.nextInt();

            int ans = test(seed, sn_max_len, nadd, npres);
            int score = (ans == correct) ? 100 : 0;
            System.out.println("#" + test + " " + score);
        }
    }

    static int seed;
    static int mrand(int num)
    {
        seed = seed * 1103515245 + 37209;
        return ((seed >>> 8) % num);
    }


    static final int SN_LEN = 1000;
    static final int MAX_SNS = 100000;
    static final int MAX_PRES = 500000;

    static StringBuilder sn;
    static int seeds[] = new int[MAX_SNS];

    static void gen_sn(int len)
    {
        sn = new StringBuilder(SN_LEN + 1);
        for(int j = 0; j < len; ++j)
        {
            final String alphabet = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int alen = alphabet.length() - 1;
            sn.append(alphabet.charAt(mrand(alen)));
        }
    }

    static int test(int _seed, int sn_max_len, int nadd, int npres)
    {
        seed = _seed;

        UserSolution.init();

        for(int i = 0; i < nadd; ++i)
        {
            seeds[i] = seed;
            gen_sn(sn_max_len);
            UserSolution.add_serial(sn.toString());
        }

        int cnt = 0;
        for(int i = 0; i < npres; ++i)
        {
            if (mrand(2) != 0)
            {
                // use existing
                int oldseed = seed;
                seed = seeds[mrand(nadd)];
                gen_sn(sn_max_len);
                seed = oldseed;
            }
            else
            {
                // create new
                gen_sn(sn_max_len);
            }

            if (UserSolution.present(sn.toString())) ++cnt;
        }

        return cnt;
    }

}
