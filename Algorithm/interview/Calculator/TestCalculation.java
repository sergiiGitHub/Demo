import static org.junit.Assert.*;

import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;


public class TestCalculation {

    @Test
    public void simpleTest() {
        Calculator calculator = new Calculator();
        String calculation = null;
        calculation = "1+2+3+4";
        assertEquals(10, calculator.calculate(calculation));

        calculation = "1+2*3+4";
        assertEquals(11, calculator.calculate(calculation));

        calculation = "1+2-3*4";
        assertEquals(-9, calculator.calculate(calculation));

        calculation = "4*2-3*4";
        assertEquals(-4, calculator.calculate(calculation));
        System.out.println("Finish simpleTest");
    }

    @Test
    public void braceTest() {

        compareWithScript( "12*(3-1)" );
        compareWithScript( "(7+3)*5" );
        compareWithScript("(10+3)*5*(3-1)");
        compareWithScript( "(((7-3)*3)*2+3+2)*5" );

        System.out.println("Finish braceTest");
    }
    
    public void compareWithScript(String createdString){
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Calculator calculator = new Calculator();
        try {
            double expected = (double) engine.eval(createdString);
            int intExpected = (int)expected;
            int actual = calculator.calculate(createdString);
            if ( intExpected != actual ){
                System.out.println(createdString + "expected: " + intExpected + "; actual: " + actual);
            }
            assertEquals(intExpected, actual);
        } catch (ScriptException e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }
    
    @Test
    public void randomTest() {
        Calculator calculator = new Calculator();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        for ( int test = 0; test < 10; ++test ){
            String createdString = create();
            try {
                double expected = (double) engine.eval(createdString);
                int intExpected = (int)expected;
                int actual = calculator.calculate(createdString);
                if ( intExpected != actual ){
                    System.out.println(createdString + "expected: " + intExpected + "; actual: " + actual);
                }
                assertEquals(intExpected, actual);
            } catch (ScriptException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
        System.out.println("Finish randomTest");
    }

    private String create() {
        final int maxValue = 100;
        final Random random = new Random();
        final char[] operation = { '*', '+', '-'};

        final StringBuilder strBuilder = new StringBuilder(String.valueOf(random.nextInt(maxValue))); 
        for (int i = 0; i < 6; ++i){
            strBuilder.append(operation[random.nextInt(operation.length)]);
            strBuilder.append(random.nextInt(maxValue ));
        }
        return strBuilder.toString();
    }

}

