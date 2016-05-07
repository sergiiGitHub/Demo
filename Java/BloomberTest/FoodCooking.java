import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {	
	private final static boolean IS_DEBUG = true;

	public static void main(String[] args) {
		Solution solution = new Solution();
		Scanner sc = Main.getScanner();
		int currentTestCase = 0;
		while ( sc.hasNext() ){			
			solution.getAnswer( ++currentTestCase, sc.nextLine() );
		}
	}

	public static Scanner getScanner() {
		Scanner sc = null;
		if ( IS_DEBUG ){
			try {
				sc = new Scanner(new File( "/home/sergii/_project/input.txt" ));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
		} else {
			sc = new Scanner(System.in);
		}
		return sc;
	}
}

class Solution{
	private static final String IDS_COMA_SPACE_SPLITER = ", ";
	private static final String IDS_SPACE_SPLITER = " ";
	private static final String IDS_COLON_SPLITER = ":";
	private static final String IDS_ORDER = "Order #";
	private static final String IDS_REJECTED = "Rejected";

	private static final int ID_TIME_INDEX = 1;
	private static final int ID_FOOD_INDEX_START = 2;

	private static final int ID_FOOD_AMOUNT_INDEX = 0;
	private static final int ID_FOOD_NAME_INDEX = 1;

	private static final int TIME_LIMIT = 600;

	private enum FoodType{
		COD,
		HADDOCK,
		CHIPS
	}

	private final FoodCooking mFoods[] = { 
			new FoodCooking(FoodType.COD, "Cod", 80),
			new FoodCooking(FoodType.HADDOCK, "Haddock", 90), 
			new FoodCooking(FoodType.CHIPS, "Chips", 120)};

	private int priviousServerOrderTime = -1;
	private String acceptTimeStr;
	private int acceptTimeInt;

	public void getAnswer(int currentTestCase, String line) {
		parse( line );

		int fFryer = getFishFryerTime( );
		if ( fFryer > TIME_LIMIT ){
			printRejected(currentTestCase);
			return;
		}

		
		//		int cFryer = getChipsFryerTime();
		//		if ( cFryer > TIME_LIMIT ){
		//			printRejected(currentTestCase);
		//			return;
		//		}

		clear();
	}

	private int getFishFryerTime() {
		FoodCooking haddock = mFoods[ FoodType.HADDOCK.ordinal() ];
		haddock.setTimeOffset(Math.max(acceptTimeInt, priviousServerOrderTime));
		FoodCooking cod = mFoods[ FoodType.COD.ordinal() ];
		int hadockTime = haddock.getTimeCooking();
		cod.setTimeOffset(hadockTime);
		if ( haddock.isVacantPlace() ){
			cod.extraFry( haddock.getVacantPlace() );
		}
		int codTime = cod.getTimeCooking();
		return Math.max(hadockTime, codTime);
	}

	private String printRejected(int currentTestCase) {
		return new StringBuilder().append(IDS_ORDER)
				.append(currentTestCase)
				.append(IDS_REJECTED)
				.toString();
	}

	private void clear() {
		for ( FoodCooking food : mFoods ){
			food.clear();
		}
	}

	private void parse(String line) {
		String split[] = line.split(IDS_COMA_SPACE_SPLITER);

		//time
		acceptTimeStr = split[ ID_TIME_INDEX ];
		acceptTimeInt = Utils.timeStringToInt( acceptTimeStr );
		log( acceptTimeStr + "; r: " + acceptTimeInt  + "; revers: " + Utils.timeIntToString(acceptTimeInt) );
		
		//food
		for( int i = ID_FOOD_INDEX_START; i < split.length; ++i ){
			String amountFoodSplite[] = split[i].split(IDS_SPACE_SPLITER);
			String name = amountFoodSplite[ID_FOOD_NAME_INDEX];

			for ( FoodCooking food : mFoods ){
				if ( isEqual( name, food.getName() ) ){
					food.setAmount(Integer.parseInt(amountFoodSplite[ID_FOOD_AMOUNT_INDEX]));
				}
			}
		}
		for ( FoodCooking food : mFoods ){
			log( food.getName() + " " + food.getAmount() );
		}
	}

	/**
	 * For current use case is acceptable , in another use HashMap  
	 */
	private boolean isEqual(String name, String name2) {
		return name.length() == name2.length();
	}

	public void log( String str ){
		System.out.println( str );
	}

	public void log( int i ){
		System.out.println( i );
	}

	class CookingOrder{
		private FoodType type;
		private int startTime;
		private int portion;
		
		public CookingOrder(FoodType type, int startTime, int portion ){
			this.type = type;
			this.startTime = startTime;
			this.portion = portion;
		}
		
		@Override
		public String toString() {
			return  "st: " + startTime +"; p" + portion + "t: " + type ;
		}
	}
	
	class FoodCooking{
		private final String name;
		private final int timeCooking;
		private final FoodType type;
		private final int place = 4;
		
		private int amount;
		private final List< CookingOrder > order;
		private int timeOffset;
		private int vacantPlace;

		public FoodCooking( FoodType type, String name, int timeCooking){
			this.type = type;
			this.name = name;
			this.timeCooking = timeCooking;
			order = new ArrayList<>();
		}
		
		public void extraFry(int vacantPlace) {
			if ( getAmount() == 0 ){
				return;
			}
			if ( getAmount() >= vacantPlace){
				setAmount(getAmount() - vacantPlace);
				order.add( new CookingOrder( type, timeOffset - timeCooking, vacantPlace) );
			} else {
				order.add( new CookingOrder( type, timeOffset - timeCooking, getAmount()) );
				setAmount(0);
			}	
		}

		public int getVacantPlace() {
			return vacantPlace;
		}

		public boolean isVacantPlace() {
			return vacantPlace != 0;
		}

		public void setTimeOffset(int timeOffset) {
			this.timeOffset = timeOffset;
		}

		public String getName(){
			return name;
		}
		
		public int getTimeCooking(){
			if ( amount == 0 ){
				return timeOffset;
			}		
			fillOrder( amount );
			return order.size()*timeCooking;
		}
		
		private void fillOrder( int amount ) {	
			int fullPortionAmount = amount/place;
			int partPortion = amount%place;
			for ( int i = 0; i < fullPortionAmount; ++i){
				order.add( new CookingOrder( type, getStartTime(i), place ) );
			}
			if ( partPortion > 0 ){
				setVacantPlace( place - partPortion );//only for fish
				order.add( new CookingOrder( type, getStartTime(fullPortionAmount), partPortion ) );
			}
		}

		private void setVacantPlace(int vacantPlace) {
			this.vacantPlace = vacantPlace;
		}

		public void setAmount( int amount ){
			this.amount = amount;
		}

		public int getAmount(){
			return amount;
		}

		public void clear(){
			setAmount( 0 );
			setVacantPlace(0);
			setTimeOffset(0);
			order.clear();
		}
		
		public int getStartTime(int i) {
			return i * timeCooking + timeOffset;
		}

		public List<CookingOrder> getOrder(){
			return order;
		}
	}
	
	static class Utils{
		
		private static final int timeConver[] = {360, 60, 1};
		
		private static int timeStringToInt(String time) {
			String spliteTime[] = time.split(IDS_COLON_SPLITER);
			int result = 0;
			for ( int i = 0; i < spliteTime.length; ++i ){
				int v = Integer.parseInt(spliteTime[i]);
				if ( v!=0 ){
					result += v * timeConver[i];
				}
			}
			return result;
		}

		private static String timeIntToString(int time) {
			StringBuilder sb = new StringBuilder();
			int size = timeConver.length;
			for ( int i = 0; i < size; ++i ){
				int temp = time / timeConver[i];
				time -= temp * timeConver[i];
				String str = String.valueOf(temp);
				if ( str.length() == 1 ){
					sb.append(0);
				}
				sb.append(str);
				if ( i != ( size - 1 ) ){
					sb.append(IDS_COLON_SPLITER);
				}
			}
			return sb.toString();
		}
		
	}

}
