
import java.util.Scanner;

public class IntegerObjectIssue{
	
	public static void main(String[] args) {
		Integer i1 = 13;
		Integer i2 = 13;
		System.out.println(  i1 + " " + i2 + " " +  (i1 == i2) );
		
		i1 = 130;
		i2 = 130;
		System.out.println(  i1 + " " + i2 + " " +  (i1 == i2) );
	}
	
	

}
