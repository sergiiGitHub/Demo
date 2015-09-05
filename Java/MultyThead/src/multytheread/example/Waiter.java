package multytheread.example;

import multytheread.example.Main.ICounter;
import multytheread.example.Main.IWaiter;

public class Waiter implements Runnable, IWaiter {
	
	static String LOG = Waiter.class.getSimpleName();
	
	static final int MIN_VALUE = 2;
	static final long TIME_WAIT = 500;
	
	final ICounter mCounter;
	
	public Waiter( ICounter aCounter ) {
		mCounter = aCounter;
	}

	public void run() {
		while ( mCounter.getValue() < MIN_VALUE ){
//			waitLocal();
			waitUntillNotify();
		}
		System.out.println( LOG + " :: Finished " );
	}

	private synchronized void waitUntillNotify() {
		try {
			System.out.println( LOG + " :: Waiting " );
			wait();
		} catch (InterruptedException e) {
			System.out.println( LOG + " :: Interupted " );
			e.printStackTrace();
		}
		System.out.println( LOG + " :: Waked up " );
		
	}

	/**
	 * wait TIME_WAIT, than wake up
	 * realization not good
	 * 
	 */
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
	public int getValueWaitFor() {
		return MIN_VALUE;
	}	
}
