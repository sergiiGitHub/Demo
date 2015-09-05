package multytheread.example;

public class Main {
	
	public interface ICounter{
		int getValue();
		void increaseValue();
		void addWaiter( IWaiter aWaiter );
	}
	
	public interface IWaiter{
		int getValueWaitFor();
	}
	
	public static void main(String[] args) {

		Counter counter = new Counter();
		Thread threadIncrease = new Thread( counter );
		
		Waiter waiter = new Waiter(counter);
		Thread threadWait = new Thread( waiter );

		counter.addWaiter( waiter );

		threadIncrease.start();
		threadWait.start();
	}
}
