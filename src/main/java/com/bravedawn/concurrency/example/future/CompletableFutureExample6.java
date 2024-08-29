package com.bravedawn.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/11 10:43
 *
 * 组合多个CompletableFuture，通过thenCombine合并多个任务的结果
 */
@Slf4j
public class CompletableFutureExample6 {

    public static Integer calc(Integer param) {
        log.info("calc start");
        return param / 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> intFuture = CompletableFuture.supplyAsync(() -> calc(50));
        CompletableFuture<Integer> intFuture2 = CompletableFuture.supplyAsync(() -> calc(25));

        CompletableFuture<Void> fu = intFuture.thenCombine(intFuture2, Integer::sum)
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        fu.get();
    }
}
