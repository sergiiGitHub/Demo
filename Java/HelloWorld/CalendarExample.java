import java.util.Calendar;
import java.util.Date;

public class CalendarExample{

	public static void main(String[] args) {

		Date date = new Date(1000000000000L);
		System.out.println("in:\t" + date.toString());
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		System.out.println("out:\t" + c.getTime().toString());
		
	}
}

