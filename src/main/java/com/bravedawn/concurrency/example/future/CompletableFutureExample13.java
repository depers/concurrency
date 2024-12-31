package com.bravedawn.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * 需要并行运行多个互不相关的任务，这些任务之间没有依赖关系，可以互相独立地运行。可以使用`anyOf()`函数来做。
 * 但是anyOf() 方法不会等待所有的 CompletableFuture 都运行完成之后再返回，只要有一个执行完成即可！
 */

@Slf4j
public class CompletableFutureExample13 {


    public static void main(String[] args) {
        Random rand = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("future1 done...");
            }
            return "abc";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("future2 done...");
            }
            return "efg";
        });

        CompletableFuture<Object> completableFuture = CompletableFuture.anyOf(future1, future2);
        // 调用 join() 可以让程序等future1 和 future2 都运行完了之后再继续执行。
        completableFuture.join();
        log.info("执行情况：{}", completableFuture.isDone());
        log.info("all futures done...");


    }
}
