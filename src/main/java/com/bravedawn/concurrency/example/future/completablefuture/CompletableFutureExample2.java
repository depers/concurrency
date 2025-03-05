package com.bravedawn.concurrency.example.future.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/8 21:57
 *
 * supplyAsync方法的使用
 */
@Slf4j
public class CompletableFutureExample2 {

    public static Integer calc(Integer param) {
        log.info("开始执行计算逻辑");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("计算结束");
        return param * param;

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // supplyAsync会创建一个新的线程去执行
        // supplyAsync用于执行有返回值的场景
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> calc(50));
        log.info("计算结果：{}", future.get());
    }
}
