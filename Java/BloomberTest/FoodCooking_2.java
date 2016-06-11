import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

enum FoodType{
	COD,
	HADDOCK,
	CHIPS
}

public class Solution{
	
	private static final String IDS_COMA_SPACE_SPLITER = ", ";
	private static final String IDS_BEGIN_COOKING = "Begin Cooking ";
	private static final String IDS_SPACE_SPLITER = " ";
	private static final String IDS_ORDER = "Order #";
	private static final String IDS_AT = "at ";
	private static final String IDS_REJECTED = " Rejected";
	private static final String IDS_ACCEPTED = " Accepted";
	private static final String IDS_SERVE = "Serve ";

	private static final int ID_TIME_INDEX = 1;
	private static final int ID_FOOD_INDEX_START = 2;

	private static final int ID_FOOD_AMOUNT_INDEX = 0;
	private static final int ID_FOOD_NAME_INDEX = 1;

	private static final int TIME_LIMIT = 600;
	private static final int TIME_LIMIT_ORDER = 120;

	private final FoodCooking mFoods[] = { 
			new FoodCooking(FoodType.COD, "Cod", 80),
			new FoodCooking(FoodType.HADDOCK, "Haddock", 90), 
			new FoodCooking(FoodType.CHIPS, "Chips", 120)};
	private final FishFrayeBehavior fishFryer;
	private final FrayeBehavior chipsFryer;
	private List< CookingOrder > orders;

	private int priviousServerOrderTime = -1;
	private String acceptTimeStr;
	private int acceptTimeInt;
	
	public Solution(){
		fishFryer = new FishFrayeBehavior();
		chipsFryer = new FrayeBehavior();
		orders = new ArrayList<>();
	}
	
	private final static boolean IS_DEBUG = true;

	public static void main(String[] args) {
		Solution solution = new Solution();
		Scanner sc = getScanner();
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
	
	private void populateBehaviour() {
		chipsFryer.set(mFoods[FoodType.CHIPS.ordinal()]);
		fishFryer.set(mFoods[FoodType.HADDOCK.ordinal()], mFoods[FoodType.COD.ordinal()]);
	}

	public void getAnswer(int currentTestCase, String line) {
		parse( line );
		populateBehaviour();
		if ( buildOrdering() ){
			printSolution(currentTestCase);
		}else {
			printRejected(currentTestCase);
		}
		clear();
	}

	private void printSolution(int currentTestCase) {
		printAccept(currentTestCase);
		for ( CookingOrder order : orders ){	
			printOrder( order );
		}
		printServe(currentTestCase);
	}

	private boolean buildOrdering() {
		
		int fishTimeFryer = fishFryer.getTime();
		int chipsTimeFryer = chipsFryer.getTime();
		if ( fishTimeFryer > TIME_LIMIT || chipsTimeFryer > TIME_LIMIT ){
			return false;
		}
		
		int curretntOffset = Math.max(acceptTimeInt, priviousServerOrderTime);
		if ( chipsTimeFryer > fishTimeFryer ){
			if ( (curretntOffset + chipsTimeFryer - acceptTimeInt) > TIME_LIMIT ){
				return false;
			}
			chipsFryer.fillOrder( curretntOffset, orders );
			int chipsSize = orders.size(); 
			if (chipsSize > 2 ) {
				return false;
			}
			fishFryer.fillOrder( curretntOffset + chipsTimeFryer - fishTimeFryer, orders );
			if ( ( orders.size() - chipsSize ) > 2 ){
				return false;
			}
			priviousServerOrderTime = curretntOffset + chipsTimeFryer;
		} else {
			if ( (curretntOffset + fishTimeFryer - acceptTimeInt) > TIME_LIMIT ){
				return false;
			}
			fishFryer.fillOrder( curretntOffset, orders );
			int chipsSize = orders.size();
			if (chipsSize > 2 ) {
				return false;
			}
			chipsFryer.fillOrder( curretntOffset + fishTimeFryer - chipsTimeFryer, orders );
			if ( ( orders.size() - chipsSize ) > 2 ){
				return false;
			}
			priviousServerOrderTime = curretntOffset + fishTimeFryer;
		}
		Collections.sort(orders);
		return true;
	}

	//at 12:00:00, Begin Cooking 4 Haddock
	private void printOrder(CookingOrder order) {
		StringBuilder sb = new StringBuilder(IDS_AT);
		sb.append(Utils.timeIntToString(order.startTime));
		sb.append(IDS_COMA_SPACE_SPLITER);
		sb.append(IDS_BEGIN_COOKING);
		sb.append(order.portion);
		sb.append(IDS_SPACE_SPLITER);
		sb.append(mFoods[order.type.ordinal()].getName());
		log( sb.toString() );
	}

	//at 12:01:00, Order #3 Rejected
	private void printRejected(int currentTestCase) {
		StringBuilder sb = new StringBuilder(IDS_AT);
		sb.append(acceptTimeStr);
		sb.append(IDS_COMA_SPACE_SPLITER);
		sb.append(IDS_ORDER);
		sb.append(currentTestCase);
		sb.append(IDS_REJECTED);
		log( sb.toString() );
	}
	
	//at 12:00:00, Order #1 Accepted
	private void printAccept(int currentTestCase) {
		StringBuilder sb = new StringBuilder(IDS_AT);
		sb.append(acceptTimeStr);
		sb.append(IDS_COMA_SPACE_SPLITER);
		sb.append(IDS_ORDER);
		sb.append(currentTestCase);
		sb.append(IDS_ACCEPTED);
		log( sb.toString() );
	}
	
	//at 12:00:00, Serve Order #1 
	private void printServe(int currentTestCase) {
		StringBuilder sb = new StringBuilder(IDS_AT);
		sb.append(Utils.timeIntToString(priviousServerOrderTime));
		sb.append(IDS_COMA_SPACE_SPLITER);
		sb.append(IDS_SERVE);
		sb.append(IDS_ORDER);
		sb.append(currentTestCase);
		log( sb.toString() );
	}

	private void clear() {
		for ( FoodCooking food : mFoods ){
			food.clear();
		}	
		orders.clear();
		fishFryer.clear();
		chipsFryer.clear();
	}

	private void parse(String line) {
		String split[] = line.split(IDS_COMA_SPACE_SPLITER);

		//time
		acceptTimeStr = split[ ID_TIME_INDEX ];
		acceptTimeInt = Utils.timeStringToInt( acceptTimeStr );
		//log( acceptTimeStr + "; r: " + acceptTimeInt  + "; revers: " + Utils.timeIntToString(acceptTimeInt) );
		
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
//		if ( IS_DEBUG ){
//			for ( FoodCooking food : mFoods ){
//				log( food.getName() + " " + food.getAmount() );
//			}
//		}
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
}

class CookingOrder implements Comparable<CookingOrder>{
	FoodType type;
	int startTime;
	int portion;
	
	public CookingOrder(FoodType type, int startTime, int portion ){
		this.type = type;
		this.startTime = startTime;
		this.portion = portion;
	}

	
	@Override
	public int compareTo(CookingOrder other) {
		return Integer.compare(startTime, other.startTime);
	}
}

class FoodCooking{
	private final String name;
	private final int timeCooking;
	private final FoodType type;
	
	private int amount;

	public FoodCooking( FoodType type, String name, int timeCooking){
		this.type = type;
		this.name = name;
		this.timeCooking = timeCooking;
	}
	
	public String getName(){
		return name;
	}
	
	public void setAmount( int amount ){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void clear(){
		setAmount( 0 );
	}

	public int getTimeCooking() {
		return timeCooking;
	}

	public FoodType getType() {
		return type;
	}
}

class FrayeBehavior{
	protected final int place = 4;
	private int timeCooking = -1;
	private FoodCooking food;

	protected int foodFull;
	protected int foodPart;
	
		
	public void set( FoodCooking food ) {
		this.food = food;
	}

	public void fillOrder(int timeOffset, List<CookingOrder> order) {
		for ( int i = 0; i < foodFull; ++i){
			order.add( new CookingOrder( food.getType(),
					calculateTime( i, timeOffset ),
					place ) );
		}
		if ( foodPart > 0 ){
			order.add( new CookingOrder( food.getType(),
					calculateTime( foodFull, timeOffset ),
					foodPart ) );
		}
	}

	protected int calculateTime(int i, int timeOffset) {
		return i * food.getTimeCooking() + timeOffset;
	}

	public int getTime( ) {
		if ( timeCooking != -1){
			return timeCooking; 
		}
		
		if ( food == null ){
			timeCooking = 0;
			return timeCooking;
		}
		int result = 0;
		if ( food.getAmount() != 0 ){
			foodFull = ( food.getAmount() / place );
			foodPart = ( food.getAmount() % place );
			result = foodFull;
			if ( foodPart != 0 ){
				++result;
			}
		}
		timeCooking = result*food.getTimeCooking();
		return timeCooking;
	}

	public void clear() {
		foodFull = 0;
		foodPart = 0;
		timeCooking = -1;
	}
}

class FishFrayeBehavior extends FrayeBehavior{
	
	private FoodCooking cod;

	private int codOffset;
	private int codFull;
	private int codPart;
	
	public void set( FoodCooking haddock, FoodCooking cod ) {
		set(haddock);
		this.cod = cod;
	}
	
	@Override
	public void clear(){
		super.clear();
		codOffset = 0;
		codFull = 0;
		codPart = 0;
	}
	
	@Override
	public int getTime( ) {
		
		int resultTime = super.getTime();
		if ( cod == null ){
			return 0;
		}
		int result = 0;
		if ( cod.getAmount() != 0 ){
			int amount = cod.getAmount();
			if ( foodPart != 0 ){
				codOffset = place - foodPart;
				if ( codOffset < amount ){
					amount -= codOffset;
				} else {
					codOffset = amount;
					amount = 0;
				}
			}

			if ( amount != 0 ){
				codFull = ( amount / place );
				codPart = ( amount  % place );
				result = codFull;
				if ( codPart != 0 ){
					++result;
				}
			}
		}

		return resultTime + result*cod.getTimeCooking();
	}
	
	@Override
	public void fillOrder(int timeOffset, List<CookingOrder> order) {
		super.fillOrder(timeOffset, order);
		
		timeOffset += super.getTime();
		if ( codOffset != 0  ){
			order.add( new CookingOrder( cod.getType(), timeOffset - cod.getTimeCooking(), codOffset ) );
		}
		for ( int i = 0; i < codFull; ++i){
			order.add( new CookingOrder( cod.getType(), calculateTime( i, timeOffset ),	place ) );
		}
		if ( codPart > 0 ){
			order.add( new CookingOrder( cod.getType(), calculateTime( codFull, timeOffset ), codPart ) );
		}
	}
}

class Utils{
	
	private static final String IDS_COLON_SPLITER = ":";
	
	private static final int timeConver[] = {3600, 60, 1};
	
	public static int timeStringToInt(String time) {
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

	public static String timeIntToString(int time) {
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
