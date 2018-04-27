package org.hejwo.threads.b_completeableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completedFuture = CompletableFuture.completedFuture(42);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(LongRunningService::getMeResult);
        Future<String> futureOld = CompletableFuture.supplyAsync(LongRunningService::getMeResult);

        future.thenAccept(System.out::println);


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
