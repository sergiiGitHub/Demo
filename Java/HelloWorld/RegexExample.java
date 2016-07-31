import java.util.regex.*;

public class RegexExample{

	public static void main(String[] args) {

		// part 1
		Pattern p = Pattern.compile("ab");
		// the expression
		Matcher m = p.matcher("abaaaba");
		// the source
		System.out.println("ab");
		while(m.find()) {
			System.out.print( m.start() + " ");
		}
		
		// part 2
		System.out.println();
		p = Pattern.compile("0[xX][0-9a-fA-F]");
		m = p.matcher("12 0x 0x12 0Xf 0xg");
		System.out.println("0[xX][0-9a-fA-F]:");
		while(m.find()) {
			System.out.print( m.start() + " ");
		}	
		
		System.out.println();
		p = Pattern.compile("12[^3]"); // except 3 ^, 
		m = p.matcher("123124123");
		System.out.println("12[^3]");
		while(m.find()) {
			System.out.print(m.start() + " ");
		}
		
		System.out.println();
		p = Pattern.compile(".*xx"); // except 3 ^, 
		m = p.matcher("yyxxxyxx");
		System.out.println(".*xx");
		while(m.find()) {
			System.out.print(m.start() + " ");
		}
		
		
		System.out.println();
		p = Pattern.compile(".*?xx"); // except 3 ^, 
		m = p.matcher("yyxxxyxx");
		System.out.println(".*?xx");
		while(m.find()) {
			System.out.print(m.start() + " ");
		}
	}
}

