package com.bravedawn.concurrency.example.future.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/11 11:24
 *
 * 支持timeout的CompletableFuture
 */

@Slf4j
public class CompletableFutureExample8 {

    public static Integer calc(Integer param) {
        log.info("calc start");
        return param / 2;
    }

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Throwable e) {
                e.printStackTrace();
            }

            return calc(10);
        }).orTimeout(1, TimeUnit.SECONDS).exceptionally(e -> {
            log.error("出现异常", e);
            return 0;
        }).thenAccept(System.out::println);

        // 这里要等待2秒。不然主线程退出，默认执行CompletableFuture的线程池的线程是守护线程，会直接退出
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
    }


}
