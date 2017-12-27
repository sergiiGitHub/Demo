import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class ConcurrentCollection {

    public void run() {
        final int size = 1000;
        List<Integer> list = new ArrayList<Integer>(size);
        for (int i = 0; i < size; ++i) {
            list.add(i);
        }

        ExecutorService es = Executors.newFixedThreadPool(2);
        
        check(es, Collections.synchronizedList(list));

        check(es, new CopyOnWriteArrayList<>(list));

        
        //Not impotent because in check we lock by feature;
        //wait for execution
        es.shutdown();
        try {
            if (!es.awaitTermination(2, TimeUnit.SECONDS) ){ 
                System.out.println("!! NOT all task finish");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Main: finish");
    }

    private void check(ExecutorService es, List<Integer> syncList) {

        CountDownLatch latch = new CountDownLatch(1);

        Future<Long> res1 = es.submit(new Task(syncList, true, latch));
        Future<Long> res2 = es.submit(new Task(syncList, false, latch));

        latch.countDown();

        try {
            System.out.println("All task finish: 1: " + res1.get(2, TimeUnit.SECONDS) + "; 2: " + res2.get(2, TimeUnit.SECONDS));
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            System.out.println("exception: " + e);
        }
    }

    private static class Task implements Callable<Long> {

        private List<Integer> list;
        private CountDownLatch latch;
        private boolean isFirstHalf;

        public Task(List<Integer> list, boolean isFirstHalf, CountDownLatch latch) {
            this.list = list;
            this.isFirstHalf = isFirstHalf;
            this.latch = latch;
        }

        @Override
        public Long call() throws Exception {
            latch.await(1, TimeUnit.SECONDS);
            int start, end;
            if (isFirstHalf) {
                start = 0;
                end = list.size() / 2;
            } else {
                start = list.size() / 2;
                end = list.size();
            }

            long startTime = System.currentTimeMillis();
            for (int i = start; i < end; ++i) {
                System.out.print("isHalf: " + isFirstHalf + "; item: " + list.get(i));
                if (i % 10 == 0) {
                    list.add(101);
                }
            }
            System.out.println();
            System.out.println("isFirstHalf:" + isFirstHalf + " finish");
            return System.currentTimeMillis() - startTime;
        }
    }

    public static void main(String[] args) {
        new ConcurrentCollection().run();
    }
}