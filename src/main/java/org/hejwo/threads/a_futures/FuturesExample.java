package org.hejwo.threads.a_futures;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FuturesExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        example1();
        example2();
    }

    private static void example1() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> submit = executor.submit(LongRunningService::getMeResult);
        System.out.println("#awesome !!! #nonBlocking !!!");
        System.out.println(submit.get());
        executor.shutdown();
    }

    private static void example2() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ExecutorCompletionService ecs = new ExecutorCompletionService(executor);

        Future<String> res1 = ecs.submit(LongRunningService::getMeResult);

        System.out.println(ecs.poll()); // Will give you null if task is not completed
        System.out.println(ecs.take()); // Will wait for fist future finish

        executor.shutdown();
    }

    private static void example3() {
        ForkJoinTask<String> submit = ForkJoinPool.commonPool().submit(LongRunningService::getMeResult);
    }

}

class LongRunningService {
    public static String getMeResult() {
        try {
            Thread.sleep(9000);
        } catch(Exception ex) {
            System.err.println("LongRunningService.getMeResult FAILED");
        }
        return "THE RESULT";
    }
}
