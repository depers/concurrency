package com.bravedawn.concurrency.example.future.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/8 22:04
 *
 * thenApply流式调用
 */

@Slf4j
public class CompletableFutureExample3 {

    public static Integer calc(Integer param) {
        log.info("calc start");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("calc end");
        return param * param;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> calc(50))
                .thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(log::info);
        log.info("main thread");
        future.get();
    }
}
