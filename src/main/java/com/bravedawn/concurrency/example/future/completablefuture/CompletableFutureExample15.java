package com.bravedawn.concurrency.example.future.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description : TODO
 * @Author : depers
 * @Project : concurrency
 * @Date : Created in 2025-03-05 11:36
 */
@Slf4j
public class CompletableFutureExample15 {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("开始执行逻辑任务1");
            log.info("开始执行逻辑任务1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        futures.add(future);

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("开始执行逻辑任务2");
            log.info("开始执行逻辑任务2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        futures.add(future2);

        CompletableFuture<Void> future3 = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        future3.thenRun(() -> {
            log.info("任务已全部执行完成");
            System.out.println("任务已全部执行完成");
        });


        future3.join();

    }
}
