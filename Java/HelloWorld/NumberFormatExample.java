
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberFormatExample{

	public static void main(String[] args) {
		float f1 = 123.45678f;
		NumberFormat nf = NumberFormat.getInstance();
		System.out.print(nf.getMaximumFractionDigits() + " ");
		System.out.print(nf.format(f1) + " ");
		nf.setMaximumFractionDigits(5);
		System.out.println(nf.format(f1) + "");
		try {
			System.out.println(nf.parse("1234.567"));
			nf.setParseIntegerOnly(true);
			System.out.println(nf.parse("1234.567"));
		} catch (ParseException pe) {
			System.out.println("parse exc");
		}
		
		/*
		 * Currency
		 */
		// France
		Locale locFR = new Locale("fr");
		NumberFormat[] nfa = new NumberFormat[4];
		nfa[0] = NumberFormat.getInstance();
		nfa[1] = NumberFormat.getInstance(locFR);
		nfa[2] = NumberFormat.getCurrencyInstance();
		nfa[3] = NumberFormat.getCurrencyInstance(locFR);
		for(NumberFormat nfItem : nfa){
			System.out.println(nfItem.format(f1));
		}
	}
}

