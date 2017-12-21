public class Main {

	public static void main(String[] args) {
		Thread thread = new Thread(new Hello(5, "1 "));
		thread.start();
		Hello hello = new Hello(3, "2 ");
		hello.run();
	}

	public static void waitLocal(Object locker, int delta) {
		while (delta > 0) {
			try {
				System.out.println(" waiting");
				synchronized (locker) {
					locker.wait(100);
				}
				// Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error");
			}
			delta--;
		}
	}
}

class Hello implements Runnable {

	private static int counter = 0;
	private final String mPrefix;
	private final int mDelay;

	public Hello(int aDelay, String aPrefix) {
		mDelay = aDelay;
		mPrefix = aPrefix;
	}

	public synchronized static void someStaticMethod(String prefix, int delay) {
		synchronized (Hello.class) {
			System.out.println(prefix
					+ " Hello :: someStaticMethod :: count =  " + counter);
			Main.waitLocal(Hello.class, delay);
			counter++;
		}
	}

	synchronized void doSmth() {
		synchronized (this) {
			System.out.println(mPrefix + "Hello :: doSmth :: count = "
					+ counter);
			counter++;
			Main.waitLocal(this, mDelay);
		}
	}

	@Override
	public void run() {
		while (counter < 15) {
			// System.out.println( "Hello :: counter = " + counter );
			// counter++;
			// doSmth(); // NONstatic
			someStaticMethod(mPrefix, mDelay); // static
			// MultiThread.waitLocal( this, mDelay );
		}
		System.out.println("thread :: counter :: finish ");
	}

}