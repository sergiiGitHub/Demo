
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataFormatExample{

	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();
		c.set(2010, 11, 14);
		
		// December 14, 2010
		// (month is 0-based
		Date d2 = c.getTime();
		Locale locIT = new Locale("it", "IT");
		Locale locPT = new Locale("pt"); 
	
		DateFormat dfUS = DateFormat.getInstance();
		System.out.println("US " + dfUS.format(d2));
		DateFormat dfUSfull = DateFormat.getDateInstance(DateFormat.FULL);
		System.out.println("US full " + dfUSfull.format(d2));
		
		DateFormat dfIT = DateFormat.getDateInstance(DateFormat.FULL, locIT);
		System.out.println("Italy" + dfIT.format(d2));
		
		DateFormat dfPT = DateFormat.getDateInstance( DateFormat.FULL, locPT);
		System.out.println("Portugal " + dfPT.format(d2));
	}
}

