import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//!! for holidays.txt see at lessons1
public class HashMapExample {

	private final HashMap<Date, List<Holiday>> holidays;
	private final WeakHashMap<String, String> cashingName;
	private final SimpleDateFormat sdf;

	public HashMapExample() {
		holidays = new HashMap<>();
		sdf = new SimpleDateFormat("yyyy/mm/dd");
		cashingName = new WeakHashMap<>();
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
			addToHolidays(date, m.group(2), m.group(3));
		}
	}

	private void addToHolidays(Date date, String name, String country) {

		List<Holiday> list = holidays.get(date);
		if (list == null) {
			list = new LinkedList<>();
			holidays.put(date, list);
		}
		String cashValue = cashingName.get(country);
		if (cashValue == null) {
			cashValue = country;
			cashingName.put(country, country);
		}
		list.add(new Holiday(date, name, cashValue));
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

		// today
		List<Holiday> list = holidays.get(date);
		System.out.println("> Today: size: " + list.size() + ": " + list);

	}

	private static class Holiday {

		private final Date date;
		private final String country;
		private final String name;

		public Holiday(Date date, String name, String country) {
			this.date = date;
			this.name = name;
			this.country = country;
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
			sb.append(", ");
			sb.append(country.hashCode());
			sb.append("::");
			sb.append(country.toString());
			sb.append("]");

			return sb.toString();
		}
	}

	public static void main(String[] args) {
		HashMapExample treeSetExample = new HashMapExample();
		System.out.println(treeSetExample.holidays);

		treeSetExample.print("2013/2/12");
	}
}
