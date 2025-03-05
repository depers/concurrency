package com.bravedawn.concurrency.example.future.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @Description : TODO
 * @Author : depers
 * @Project : concurrency
 * @Date : Created in 2025-03-05 11:24
 */
public class CompletableFutureExample14 {

    /**
     * runAsync()方法的使用，这个方法用来执行没有返回值的逻辑
     */

    private static void calculate() throws InterruptedException {
        System.out.println("开始执行计算逻辑");
        Thread.sleep(1000);
        System.out.println("计算逻辑执行结束");
    }
    public static void main(String[] args) {

        CompletableFuture<?> future = CompletableFuture.runAsync(() -> {
            try {
                calculate();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        future.join();

    }
}
