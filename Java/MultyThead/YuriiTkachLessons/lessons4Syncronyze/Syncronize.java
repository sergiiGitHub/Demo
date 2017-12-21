import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Syncronize {

	private static final long WAIT_SEC = 1;
	private static final int TARNSFER_A_B = 4;
	private static final int TARNSFER_B_A = 2;

	public static void main(String[] args) {
		final Account a = new Account(1000);
		final Account b = new Account(1000);
		final long transferAbStart = System.currentTimeMillis();

		// For launch all at one time
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		// For launch when all A > B finish than start B > A.
		final CountDownLatch countABEnd = new CountDownLatch(TARNSFER_A_B);
		// For print out when all A > B finish
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(TARNSFER_A_B,
				new Runnable() {
					@Override
					public void run() {
						System.out.println("TARNSFER_A_B: finish "
								+ (System.currentTimeMillis() - transferAbStart)
								+ " ms");
					}
				});

		final ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < TARNSFER_A_B; ++i) {
			int nextInt = new Random().nextInt(250);
			Transfer task = new Transfer(a, b, nextInt, countDownLatch, true);
			Transfer.ITransferListener transferListener = new Transfer.ITransferListener() {

				@Override
				public void onSuccess() {
					System.out.println("transferListener: OnSuccess");
					countABEnd.countDown();
					try {
						cyclicBarrier.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						// TODO Auto-generated catch block
						System.out
								.println("transferListener: OnSuccess: Interapted: e:"
										+ e);
					}
				}

				@Override
				public void onFail() {
					System.out.println("transferListener: onFail");
				}
			};
			task.setTransferListener(transferListener);
			executorService.submit(task);
		}

		for (int i = 0; i < TARNSFER_B_A; ++i) {
			int nextInt = new Random().nextInt(250);
			executorService.submit(new Transfer(b, a, nextInt, countABEnd,
					false));
		}

		// Launch all in one
		countDownLatch.countDown();

		waitForAll(executorService, (TARNSFER_A_B + TARNSFER_B_A) * 2,
				transferAbStart);
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

		private final Account from;
		private final Account to;
		private final int amount;
		private final CountDownLatch startLatch;
		private final boolean isRetry;
		private ITransferListener transferListener;

		public Transfer(Account from, Account to, int amount,
				CountDownLatch startLatch, boolean isRetry) {
			this.from = from;
			this.to = to;
			this.amount = amount;
			this.startLatch = startLatch;
			this.isRetry = isRetry;
		}

		@Override
		public Boolean call() throws Exception {
			startLatch.await();
			while (true) {
				if (transfer(from, to, amount)) {
					if (transferListener != null) {
						transferListener.onSuccess();
					}
					return true;
				} else if (!isRetry) {
					transferListener.onFail();
					return false;
				} else {
					System.out.println("Retry");
				}
			}
		}

		public ITransferListener getTransferListener() {
			return transferListener;
		}

		public void setTransferListener(ITransferListener transferListener) {
			this.transferListener = transferListener;
		}

		public interface ITransferListener {
			void onSuccess();

			void onFail();
		}
	}

	// l.lock();
	// l.tryLock(200, TimeUnit.MILLISECONDS);
	//
	static boolean transfer(Account acc1, Account acc2, int a) throws Exception {

		if (acc1.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
			try {
				if (acc2.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
					try {

						if (acc1.getBalance() < 500 || acc2.getBalance() < 500) {
							throw new IllegalStateException();
						}
						System.out.println("transfer in acc1: " + acc1
								+ "; acc2: " + acc2);
						acc1.withdrow(a);
						heavyAction();
						acc2.deposit(a);
						System.out.println("transfer out acc1: " + acc1
								+ "; acc2: " + acc2);
						return true;
					} finally {
						acc2.getLock().unlock();
					}
				} else {
					System.out.println("Error waiting acc2");
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
		return false;
	}

	private static void heavyAction() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
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