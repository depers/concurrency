package com.bravedawn.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/11 11:16
 *
 * 通过`handle()`方法可以对任务执行的结果进行处理，包括执行结果和异常
 */

@Slf4j
public class CompletableFutureExample7 {


    public static Integer calc(Integer param) {
        log.info("calc start");
        return 100 / param;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> intFuture = CompletableFuture.supplyAsync(() -> calc(0));
        CompletableFuture<Integer> resFuture = intFuture.handle((res, e) -> {
            if (res != null) {
                return res;
            }
            if (e != null) {
                log.error("计算出错", e);
            }

            return 0;
        });

        resFuture.get();
    }
}
