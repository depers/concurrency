package com.bravedawn.concurrency.example.future.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/8 22:11
 *
 * exceptionally异常处理
 */

@Slf4j
public class CompletableFutureExample4 {

    public static Integer calc(Integer param) {
        return param / 0;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> calc(50))
                .exceptionally(ex -> {
                    log.error(ex.toString());
                    return 0;
                })
                .thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(log::info);

        future.get();
    }
}
