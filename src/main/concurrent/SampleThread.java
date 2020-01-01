package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SampleThread {

    private static final int NTHREDS = 10;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < 1000 ; i++){
            Runnable task = new CountingRunnableTask(10000000L + i);

            Thread worker = new Thread(task);

            worker.setName(String.valueOf(i));

            // Start the thread, never call method run() direct
            worker.start();
            // Remember the thread for later usage
            threads.add(worker);
        }

        int running;
        do {
            running = 0;
            for (Thread thread : threads) {
                if (thread.isAlive())
                    running++;
            }
            System.out.println("We have " + running + " running threads. ");
        } while (running > 0);





        //Using threadPool
        System.out.println("Using threadPool");

        ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
        for (int i = 0; i < 500; i++) {
            Runnable worker = new CountingRunnableTask( i);
            executor.execute(worker);
        }
        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();
        // Wait until all threads are finish
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Finished all threads");
    }


    public static class CountingRunnableTask implements Runnable {
        private final long countUntil;

        CountingRunnableTask(long countUntil) {
            this.countUntil = countUntil;
        }

        @Override
        public void run() {
            long sum = 0;
            for (long i = 1; i < countUntil; i++) {
                sum += i;
            }
            System.out.println(sum);
        }
    }

}