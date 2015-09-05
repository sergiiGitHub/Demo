
package multytheread.example;

import java.util.HashMap;
import java.util.Map;

import multytheread.example.Main.ICounter;
import multytheread.example.Main.IWaiter;

public class Counter implements Runnable, ICounter {
	
	private static String LOG = Counter.class.getSimpleName();

	private static  final long TIME_WAIT = 800;	
	private static final int MAX_VALUE = 5;
	
	private final Map<Integer, IWaiter> mMapOfWaiter; 

	private int value = 0;
	
	public Counter() {
		mMapOfWaiter = new HashMap<Integer, IWaiter >();
	}

	@Override
	public void run() {
		System.out.println( LOG + " :: mValue " + getValue() );
		while (getValue() < MAX_VALUE ){
			waitLocal();
			checkForNotification();
			increaseValue();
			System.out.println( LOG + " :: mValue " + getValue() );
		}
		System.out.println( LOG + " :: Finished " );
		clear();
	}
	
	private void checkForNotification() {
		final int currentValue = getValue();
		IWaiter waiter = mMapOfWaiter.get( currentValue );
		if( waiter != null ){
			mMapOfWaiter.remove( currentValue );
			synchronized (waiter) {
				waiter.notify();
			}
		}
	}

	private void clear() {
		mMapOfWaiter.clear();
	}

	private synchronized void waitLocal() {
		try {
			System.out.println( LOG + " :: Waiting " );
			wait(TIME_WAIT);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized int getValue() {
		return value;
	}

	@Override
	public synchronized void increaseValue() {
		value++;
	}

	@Override
	public void addWaiter(IWaiter aWaiter) {
		mMapOfWaiter.put(aWaiter.getValueWaitFor(), aWaiter);
	}

}
