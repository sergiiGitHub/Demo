
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DataFormatExample_2{

	public static void main(String[] args) {

		Date date = new Date(1000000000000L);
		System.out.println("in:\t" + date.toString());
		DateFormat dateFormat = DateFormat.getInstance( );
		String s = dateFormat.format(date);
		try {
			Date d2 = dateFormat.parse(s);
			System.out.println("parsed:\t" + d2.toString());
		} catch (ParseException pe) {
			System.out.println("parse exc");
		}
		
		System.out.println("tim: " + DateFormat.getTimeInstance().format(date));
		
		System.out.println("def: " + DateFormat.getDateInstance().format(date));
		System.out.println("sho: " + DateFormat.getDateInstance( DateFormat.SHORT	).format(date));
		System.out.println("mid: " + DateFormat.getDateInstance( DateFormat.MEDIUM).format(date));
		System.out.println("ful: " + DateFormat.getDateInstance( DateFormat.FULL).format(date));
		System.out.println("lng: " + DateFormat.getDateInstance( DateFormat.LONG).format(date));
		
	}
}

