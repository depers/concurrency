package com.bravedawn.concurrency.example.future.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/21 17:24
 *
 * 通过`complete()`或是``completeExceptionally()``指定任务的完成情况，通过`whencomplete()`封装任务执行完成后的逻辑
 */

@Slf4j
public class CompletableFutureExample9 {

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

        log.info("开始获取结果");
        // a.completeExceptionally(new RuntimeException("异常"));
        Thread.sleep(2000);
        a.complete("success");
    }
}
