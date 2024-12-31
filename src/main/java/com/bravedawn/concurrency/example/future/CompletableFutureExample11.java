package com.bravedawn.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * acceptEitherAsync和acceptEither的比较
 * 这两个函数会在异步任务 1 和异步任务 2 中的任意一个完成时触发执行任务 3
 * 不同的是acceptEither会在先完成任务的那个线程继续执行，而acceptEitherAsync则会异步新起一个线程去做
 */

@Slf4j
public class CompletableFutureExample11 {


    public static void main(String[] args) {
        acceptEitherAsync();
        log.info("____________________________");
        acceptEither();
    }
    
    public static void acceptEitherAsync() {
        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> {
            log.info("任务1开始执行，当前时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("任务1执行完毕，当前时间：" + System.currentTimeMillis());
            return "task1";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            log.info("任务2开始执行，当前时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("任务2执行完毕，当前时间：" + System.currentTimeMillis());
            return "task2";
        });

        task.acceptEitherAsync(task2, (res) -> {
            log.info("任务3开始执行，当前时间：" + System.currentTimeMillis());
            log.info("上一个任务的结果为：" + res);
        });

        // 增加一些延迟时间，确保异步任务有足够的时间完成
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void acceptEither() {
        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> {
            log.info("任务1开始执行，当前时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("任务1执行完毕，当前时间：" + System.currentTimeMillis());
            return "task1";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            log.info("任务2开始执行，当前时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("任务2执行完毕，当前时间：" + System.currentTimeMillis());
            return "task2";
        });

        task.acceptEither(task2, (res) -> {
            log.info("任务3开始执行，当前时间：" + System.currentTimeMillis());
            log.info("上一个任务的结果为：" + res);
        });

        // 增加一些延迟时间，确保异步任务有足够的时间完成
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
