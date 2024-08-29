package com.bravedawn.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/21 18:06
 *
 * 通过捕获get方法的超时情况，异常终止a任务的执行
 */
@Slf4j
public class CompletableFutureExample10 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> a = new CompletableFuture<>();

        a.whenComplete((res, e) -> {
            if (e != null) {
                log.info("a 任务出现异常", e);
            }

            if (res != null) {
                log.info("a 任务执行的结果是：{}", res);
            }
        });

        try {
            a.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            a.completeExceptionally(new RuntimeException("timeout"));
        }

    }
}
