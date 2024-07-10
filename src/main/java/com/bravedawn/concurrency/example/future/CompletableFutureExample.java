package com.bravedawn.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/8 21:48
 *
 * 通过使用Completable的get和complete函数来实现阻塞和通知异步线程的操作
 */

@Slf4j
public class CompletableFutureExample {

    public static class AskThread implements Runnable {

        CompletableFuture<Integer> future = null;
        public AskThread(CompletableFuture<Integer> future) {
            this.future = future;
        }

        @Override
        public void run() {
            log.info("Ask thread started");
            int myResult = 0;
            try {
                // 这里会进行阻塞
                myResult = future.get() * future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            log.info("Ask thread finished, result: {}", myResult);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();

        // 模拟耗时操作
        Thread.sleep(5000);
        // 当任务完成之后通知异步线程继续执行
        log.info("主线程执行完成");
        future.complete(60);
    }
}
