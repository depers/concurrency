package com.bravedawn.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/11 10:33
 *
 * 连续执行多个任务，通过`thenCompose`将上一步的执行结果进一步进行处理
 */

@Slf4j
public class CompletableFutureExample5 {


    public static Integer calc(Integer param) {
        log.info("calc start");
        return param / 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> fu =
                CompletableFuture.supplyAsync(() -> calc(50))
                        .thenCompose((i) -> CompletableFuture.supplyAsync(() -> calc(i)))
                        .thenApply((str) -> "\"" + str + "\"").thenAccept(System.out::println);
        fu.get();
    }
}
