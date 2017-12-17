import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	private static final long WAIT_SEC = 1;

	public static void main(String[] args) {
		final Account a = new Account(1000);
		final Account b = new Account(2000);

		new Thread(new Runnable() {
			@Override
			public void run() {
				transferLock(a, b, 500);
			}
		}).start();

		transferLock(b, a, 500);
	}

	// bed practice synchronized by Main it one. result no multithread
	static void transfer(Account acc1, Account acc2, int a)
			throws RuntimeException {
		System.out.println("transfer in");
		if (acc1.getBalance() < a) {
			throw new RuntimeException();
		}

		acc1.withdrow(a);
		acc1.deposit(a);
		System.out.println("transfer out");
	}

	// bed practice synchronized by Main it one. result no multithread
	static void transferAtomicValue(Account acc1, Account acc2, int a)
			throws RuntimeException {
		System.out.println("transfer in");
		if (acc1.getBalance() < a) {
			throw new RuntimeException();
		}

		acc1.withdrow(a);
		acc1.deposit(a);
		System.out.println("transfer out");
	}

	// dead lock
	// resolve:
	// 1. add id to account synchronized by first less than great x
	// >> not good: obtain sm similar for example same id etc
	// >> But first which try
	// 2. addition monitor
	// >> obtain not multithread
	// 3. Lock
	static void transferWithDeadLoack(Account acc1, Account acc2, int a)
			throws RuntimeException {
		System.out.println("transfer in");

		synchronized (acc1) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (acc2) {
				if (acc1.getBalance() < a) {
					throw new RuntimeException();
				}

				acc1.withdrow(a);
				acc1.deposit(a);
			}
		}
		System.out.println("transfer out");
	}

	// l.lock();
	// l.tryLock(200, TimeUnit.MILLISECONDS);
	//
	static void transferLock(Account acc1, Account acc2, int a)
			throws RuntimeException {
		System.out.println("transfer in");

		try {
			if (acc1.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
				try {
					if (acc2.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
						try {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (acc1.getBalance() < a) {
								throw new RuntimeException();
							}

							acc1.withdrow(a);
							acc1.deposit(a);
						} finally {
							acc2.getLock().unlock();
						}
					} else {
						System.out.println("Error waiting acc1");
						acc2.incFailCounter();
					}
				} catch (InterruptedException e) {
					System.out.println("transfer e: " + e);
				} finally {
					acc1.getLock().unlock();
				}
			} else {
				System.out.println("Error waiting acc1");
				acc1.incFailCounter();
			}
		} catch (InterruptedException e) {
			System.out.println("transfer e: " + e);
		}
		System.out.println("transfer out");
	}

	public static class Account {
		private int balance;
		private final Lock lock;
		private final AtomicInteger failCounter;

		public Account(int b) {
			balance = b;
			lock = new ReentrantLock();
			failCounter = new AtomicInteger();
		}

		public void incFailCounter() {
			failCounter.incrementAndGet();
		}

		public Lock getLock() {
			return lock;
		}

		public int getBalance() {
			return balance;
		}

		public void withdrow(int a) {
			balance -= a;
		}

		public void deposit(int a) {
			balance += a;
		}
	}
}