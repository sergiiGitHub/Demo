public class UserSolution {

    final static long[] xarr = new long[128];
    final static Token mToken = new Token();
    final static Tokenizer mTokenizer = new Tokenizer();
    
    public static int polynome(String string, int xnum) {
        for (int i = 0; i < xnum; i++) {
            xarr[i] = 0;
        }

        mTokenizer.currIndex = 0;
        mTokenizer.mExpression = string;
        mTokenizer.endIndex = string.length() - 1;
        
        calcX();
        
        int ret = 0;
        for (int i = 0; i < xnum; i++) {
            if (xarr[i] < 0) {
                ret++;
            }
        }
        return ret;
    }
     
    private static void calcX() {
        while (mTokenizer.hasMoreTokens()) {
            mTokenizer.fillToken(mToken);
            if (mToken.delimiter == '(') {
                openParenthesis(mToken.value);
            } else {
                addVal(mToken);
            } 
        }
    }

    private static void addVal(Token curr) {
        xarr[curr.xNum] += curr.value;
    }

    private static void openParenthesis(long mult) {
        while (mTokenizer.hasMoreTokens()) {
            mTokenizer.fillToken(mToken);
            if (mToken.delimiter == ')') {
                return;
            } else if (mToken.delimiter == '(') {
                openParenthesis(mult * mToken.value);
            } else {
                mToken.value = mToken.value * mult;
                addVal(mToken);
            }
        }
    }

    public static class Token {
        char delimiter;
        int xNum;
        long value;
                
        public Token() {
        }
        public void fill(char ch) {
            delimiter = ch;
            value = 0;
            xNum = 0;
        }
        public void fill(long val, int x) {
            delimiter = 0;
            value = val;
            xNum = x;
        }
        public void fill(int val) {
            delimiter = '(';
            value = val;
            xNum = 0;
        }                
    }
    
    static class Tokenizer {
        
        int endIndex;
        String mExpression;
        int currIndex = 0;
        
        public Tokenizer() {
        }

        public void fillToken(Token token) {
            char ch = mExpression.charAt(currIndex);
            
            if (ch == '(' || ch == ')') {
                currIndex++;
                token.fill(ch);    
            } else {
                int start = currIndex;
                if (ch == '-' || ch == '+') {
                    currIndex++;
                    ch = mExpression.charAt(currIndex);
                }
                int val = 0;
                while (ch >= '0' && ch <= '9') {
                    val = val * 10;
                    val = val + (ch - '0');
                    currIndex++;
                    ch = mExpression.charAt(currIndex);
                }
                
                if (ch == 'x') {
                    int xNum = 0;
                    currIndex++;
                    ch = mExpression.charAt(currIndex);
                    while (ch >= '0' && ch <= '9' && currIndex < endIndex) {
                        xNum = xNum * 10;
                        xNum = xNum + (ch - '0');
                        currIndex++;
                        ch = mExpression.charAt(currIndex);
                    }
                    
                    val = correctVal(start, val);
                    token.fill(val, xNum);
                    return;
        
                } else {
                    val = correctVal(start, val);
                    currIndex++;
                    token.fill(val);
                    return;
                }
            }
        }

        private int correctVal(int start, int val) {
            return mExpression.charAt(start) == '-' ? -val : val;
        }

        public boolean hasMoreTokens() {
            return currIndex < endIndex;
        }
    }
}
