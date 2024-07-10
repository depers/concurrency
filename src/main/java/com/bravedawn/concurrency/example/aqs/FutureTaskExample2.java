package com.bravedawn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskExample2 {

    /**
     * 演示ThreadPoolExecutor与FutureTask的使用
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (10, 20, 300L, TimeUnit.SECONDS,  new ArrayBlockingQueue(100));

        FutureTask<String> taskResult = (FutureTask<String>) threadPoolExecutor.submit(() -> {
            log.info("执行异步任务开始");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("执行异步任务结束");
            return "success";
        });


        if (!taskResult.isDone()) {
            log.error("中断异步任务");
            taskResult.cancel(true);
        } else {
            String s = taskResult.get();
            log.info("打印异步执行结果，result={}", s);
        }
        threadPoolExecutor.shutdown();
    }
}
