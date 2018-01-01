import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

//!! for holidays.txt see at lessons1
// add concurrent read 
// todo add weakHashMap
// Во-вторых, одновременно с загрузкой и парсингом праздников выполните подсчет количества праздников для каждого дня и каждого месяца. Для этого используйте отдельные Map для хранения того, сколько праздников будет в каждом дне и каждом месяце.
// В результате выполнения программы выведите наиболее и наименее “праздничный” день, а также количество праздников в каждом месяце
public class HashMapExample {

	private final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy/mm/dd");
	private final static Pattern p = Pattern
			.compile("(\\d+/\\d+/\\d+)\\s(.*?)\\s\\((.*?)\\)");

	private final HashMap<Date, List<Holiday>> holidays;
	private final WeakHashMap<String, String> cashingName;

	// Concurrent
	private final ConcurrentHashMap<Date, List<Holiday>> syncHolidays;
	private final Map<String, String> syncCashingName;

	public HashMapExample() {
		holidays = new HashMap<>();
		cashingName = new WeakHashMap<>();

		// sync
		syncCashingName = Collections
				.synchronizedMap(new WeakHashMap<String, String>());
		syncHolidays = new ConcurrentHashMap<>();

		List<String> readLines = read();
		if (readLines != null) {
			parse(readLines);
			parseConcurent(readLines);
		}
	}

	private void parseConcurent(List<String> readLines) {
		int half = readLines.size() / 2;
		Thread thread1 = new Thread(new ReadPartly(readLines.subList(0, half),
				syncHolidays));
		Thread thread2 = new Thread(new ReadPartly(readLines.subList(half + 1,
				readLines.size()), syncHolidays));

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main: parseConcurent: finish");
	}

	private static class ReadPartly implements Runnable {

		private final List<String> lines;
		private final ConcurrentHashMap<Date, List<Holiday>> syncHolidaysLocal;

		public ReadPartly(List<String> lines,
				ConcurrentHashMap<Date, List<Holiday>> syncHolidays) {
			this.lines = lines;
			this.syncHolidaysLocal = syncHolidays;
		}

		@Override
		public void run() {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
			for (String str : lines) {
				// 2003/2/12 text (inside text)
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
			System.out.println("Thread: finish");
		}

		private void addToHolidays(Date date, String name, String country) {

			List<Holiday> list = syncHolidaysLocal.get(date);
			if (list == null) {
				list = new LinkedList<>();
				syncHolidaysLocal.put(date, list);
			}
			// String cashValue = cashingName.get(country);
			// if (cashValue == null) {
			// cashValue = country;
			// cashingName.put(country, country);
			// }
			list.add(new Holiday(date, name, country));
		}
	}

	private void parse(List<String> readLines) {

		for (String str : readLines) {
			// 2003/2/12 text (inside text)
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

	private List<String> read() {
		List<String> lines = null;
		try {
			lines = FileUtils.readLines(new File("holidays.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		System.out.println("lines: " + lines.size());
		return lines;
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
		if (list == null) {
			System.out.println("> Today: size: null");
		} else {
			System.out.println("> Today: origin size: " + list.size() + ": "
					+ list);
		}

		List<Holiday> syncList = syncHolidays.get(date);
		if (list == null) {
			System.out.println("> Today syncList: size: null");
		} else {
			System.out.println("> Today: syncList size: " + list.size() + ": "
					+ syncList);
		}
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
