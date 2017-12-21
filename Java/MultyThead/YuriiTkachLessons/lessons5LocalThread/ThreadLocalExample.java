import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLocalExample {

	private static final long WAIT_SEC = 1;
	// generate diff id
	private static final AtomicInteger idGenerator = new AtomicInteger(0);
	private static final int OPERATION_COUNT = 10;

	public static void main(String[] args) {
		final Account a = new Account(1000);
		final Account b = new Account(1000);
		final long transferAbStart = System.currentTimeMillis();

		final ExecutorService executorService = Executors.newFixedThreadPool(3);
		final Random random = new Random();
		for (int i = 0; i < OPERATION_COUNT; ++i) {
			if (random.nextBoolean()) {
				executorService.submit(new Transfer(a, b, random.nextInt(250)));
			} else {
				executorService.submit(new Transfer(b, a, random.nextInt(250)));
			}
		}

		waitForAll(executorService, (OPERATION_COUNT) * 2, transferAbStart);
	}

	private static void waitForAll(ExecutorService executorService,
			int estimatedTarnsferTime, long transferAbStart) {
		// Waiting for all tasks to complete
		executorService.shutdown();
		try {
			boolean rezWait = executorService.awaitTermination(
					estimatedTarnsferTime, TimeUnit.SECONDS);
			if (!rezWait) {
				System.err.println("waitForAll: Not all tasks have completed.");
			}
		} catch (InterruptedException e) {
			System.err.println("waitForAll: InterruptedException: e: " + e);
		}

		System.out
				.println("waitForAll: Overall time for A->B to B > A transfers is: "
						+ (System.currentTimeMillis() - transferAbStart)
						+ " ms");
	}

	public static class Transfer implements Callable<Boolean> {

		private final Account acc1;
		private final Account acc2;
		private final int amount;
		// example of thread local for each thread hold diff value
		private ThreadLocal<Integer> threadLocal;

		public Transfer(Account acc1, Account acc2, int amount) {
			this.acc1 = acc1;
			this.acc2 = acc2;
			this.amount = amount;
		}

		@Override
		public Boolean call() throws Exception {
			this.threadLocal = new ThreadLocal<>();
			threadLocal.set(idGenerator.getAndIncrement());
			return transfer(acc1, acc2, amount);
		}

		// l.lock();
		// l.tryLock(200, TimeUnit.MILLISECONDS);
		//
		private boolean transfer(Account acc1, Account acc2, int a)
				throws RuntimeException, InterruptedException {

			// System.out.println("transfer in: id" + threadLocal.get());
			if (acc1.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
				try {
					if (acc2.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
						try {

							if (acc1.getBalance() < a) {
								throw new RuntimeException();
							}
							System.out
									.println("transfer: " + threadLocal.get()
											+ "; in: left:" + acc1
											+ ": right: " + acc2);
							acc1.withdrow(a);
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							acc2.deposit(a);
							System.out.println("transfer: " + threadLocal.get()
									+ "; out: left:" + acc1 + ": right: "
									+ acc2);
							return true;
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

			System.out.println("transfer out");
			return false;
		}

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

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Balance: ").append(balance);
			sb.append("; Fail Count: ").append(failCounter);
			// sb.append("\n");
			return sb.toString();
		}
	}
}
