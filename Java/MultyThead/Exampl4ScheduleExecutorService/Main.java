import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	private static final long WAIT_SEC = 1;

	public static void main(String[] args) {
		final Account a = new Account(1000);
		final Account b = new Account(2000);

		ExecutorService es = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; ++i) {
			int nextInt = new Random().nextInt(400);
			Transfer task = new Transfer(b, a, nextInt);
			es.submit(task);
			nextInt = new Random().nextInt(400);
			Transfer task2 = new Transfer(a, b, nextInt);
			es.submit(task2);
		}

		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		ses.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				if (a.getFail() > 1) {
					System.out.println(">> fail more 1 a");
				}
				if (b.getFail() > 1) {
					System.out.println(">> fail more 1 b");
				}
			}
		}, 1, 1, TimeUnit.SECONDS);

		es.shutdown();
		// ses.shutdown();
		try {
			ses.awaitTermination(6, TimeUnit.SECONDS);
			es.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static class Transfer implements Callable<Boolean> {

		private final Account from;
		private final Account to;
		private final int amount;

		public Transfer(Account from, Account to, int amount) {
			this.from = from;
			this.to = to;
			this.amount = amount;
		}

		@Override
		public Boolean call() throws Exception {
			return transferLock(from, to, amount);
		}
	}

	// l.lock();
	// l.tryLock(200, TimeUnit.MILLISECONDS);
	//
	static boolean transferLock(Account acc1, Account acc2, int a)
			throws RuntimeException {
		System.out.println("transfer in");

		try {
			if (acc1.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
				try {
					if (acc2.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
						try {

							if (acc1.getBalance() < a) {
								throw new RuntimeException();
							}

							acc1.withdrow(a);
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							acc1.deposit(a);
							System.out.println("transfer out");
							return true;
						} finally {
							acc2.getLock().unlock();
						}
					} else {
						System.out.println("Error waiting acc2");
						acc2.incFailCounter();
						return false;
					}
				} catch (InterruptedException e) {
					System.out.println("transfer e: " + e);
				} finally {
					acc1.getLock().unlock();
				}
			} else {
				System.out.println("Error waiting acc1");
				acc1.incFailCounter();
				return false;
			}
		} catch (InterruptedException e) {
			System.out.println("transfer e: " + e);
		}
		System.out.println("transfer out");
		return false;
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

		public int getFail() {
			return failCounter.get();
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