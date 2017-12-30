import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TreeSetExample {

	private final TreeSet<Holiday> holidays;
	private final SimpleDateFormat sdf;

	public TreeSetExample() {
		holidays = new TreeSet<>();
		// (new Comparator<Holiday>() {
		//
		// @Override
		// public int compare(Holiday hleft, Holiday hRight) {
		// return (int) (hleft.getDate().getTime() - hleft.getDate()
		// .getTime());
		// }
		// });
		sdf = new SimpleDateFormat("yyyy/mm/dd");
		read();
	}

	private void read() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("holidays.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Exception: " + e);
		}

		if (scanner == null) {
			return;
		}

		final Pattern p = Pattern
				.compile("(\\d+/\\d+/\\d+)\\s(.*?)\\s\\((.*?)\\)");

		// the expression
		int index = 0;
		while (scanner.hasNextLine()) {
			String str = scanner.nextLine(); // 2003/2/12 text (inside text)
			// System.out.println(str);
			final Matcher m = p.matcher(str);
			m.find();
			// System.out.println("is: " + m.find() + "; group: " +
			// m.groupCount()
			// + " 1. " + m.group(1) + "; 2. " + m.group(2) + "; 3. "
			// + m.group(3));

			if (m.groupCount() != 3) {
				System.out.println("SKIP: m.groupCount() != 3");
				continue;
			}

			Date date = null;
			try {
				date = sdf.parse(m.group(1));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (date == null) {
				System.out.println("date == null");
				continue;
			}
			addToHolidays(date, m.group(2));
			if (index == 3) {
				// break;
			}
			++index;
		}
	}

	private void addToHolidays(Date date, String name) {
		Holiday newItem = new Holiday(date, name);
		Holiday old = holidays.ceiling(newItem);
		if (old != null) {
			old.append(name);
		} else {
			holidays.add(newItem);
		}
	}

	private void print(String string) {
		System.out.println("Print: ");

		Date date = null;
		try {
			date = sdf.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date == null) {
			System.out.print("Error NOT DATE");
		}
		Holiday newItem = new Holiday(date, null);

		// today
		System.out.println("> Today: " + holidays.ceiling(newItem));
		// tomorrow
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		System.out.println("> Tomorrow: "
				+ holidays.ceiling(new Holiday(date, null)));
		// 5 days
		c.setTime(date);
		c.add(Calendar.DATE, 5);
		Date endDate = c.getTime();
		System.out.println("> Next 5 days: "
				+ holidays.subSet(new Holiday(date, null), new Holiday(endDate,
						null)));

	}

	private static class Holiday implements Comparable<Holiday> {

		private final Date date;
		private String name;

		public Holiday(Date date, String name) {
			this.date = date;
			this.name = name;
		}

		public void append(String name) {
			this.name = this.name + "; " + name;
		}

		public Date getDate() {
			return date;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(date);
			sb.append("[");
			sb.append(name);
			sb.append("]");

			return sb.toString();
		}

		@Override
		public int compareTo(Holiday other) {
			return (int) (getDate().getTime() - other.getDate().getTime());
		}
	}

	public static void main(String[] args) {
		TreeSetExample treeSetExample = new TreeSetExample();
		System.out.println(treeSetExample.holidays);

		treeSetExample.print("2013/2/12");

	}
}
