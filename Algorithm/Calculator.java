import java.util.LinkedList;
// TODO add brace and check implementation on 
// http://www.geeksforgeeks.org/expression-evaluation/
// add divive operation just use double insted of int

/*
input example 1+2 - 5*3 
*/

public class Calculator {

    public int calculate(String string){
        LinkedList<Integer> low = new LinkedList<Integer>();
        parse(low, string);
        int result = 0;
        for ( Integer v : low ){
            result += v;
        }
        low.clear();
        return result;
    }

    private void parse(LinkedList<Integer> low, String string) {
        int startIndex = 0;

        char prevOperation = '+';

        for (int i = 0; i < string.length(); ++i){
            char currentChar = string.charAt(i);
            if(isOperation(currentChar)){
                int digit = Integer.valueOf(string.substring(startIndex, i));
                applyPriority(low, prevOperation, digit);
                prevOperation = currentChar;
                startIndex = i+1;
            }
        }
        int lastDigit = Integer.parseInt(string.substring(startIndex, string.length()));
        applyPriority(low, prevOperation, lastDigit);
    }

    private void applyPriority(LinkedList<Integer> low, char currnetOperation, int lastDigit) { 
        if (isHighOperation(currnetOperation)){
            Integer prev = low.pollLast();
            low.add(simpleCalculate(currnetOperation, prev, lastDigit));
        } else {
            if ( currnetOperation == '-'){
                lastDigit*=-1;
            }
            low.add(lastDigit);
        }
    }

    private int simpleCalculate(char currentOperation, int digitLeft, int digitRight) {
        if (currentOperation == '/'){
            digitLeft /= digitRight;
        } else if (currentOperation == '*'){
            digitLeft *= digitRight;
        }
        return digitLeft;
    }

    private boolean isOperation(char ch) {
        return ch == '+' || ch == '-' || isHighOperation(ch);
    }

    private boolean isHighOperation(char ch) {
        return ch == '/' || ch == '*';
    }

}
