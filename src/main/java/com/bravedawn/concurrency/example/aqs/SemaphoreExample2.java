package com.bravedawn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample2 {

    private static final int threadCount = 200;

    public static void main(String[] args) throws Exception{

        ExecutorService exec = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(3); // 每次允许三个执行

        for (int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute(() -> {
                try{
                    semaphore.acquire(3);  // 获取多个许可
                    test(threadNum);
                    semaphore.release(3);  // 释放多个许可
                } catch (Exception e){
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadCount) throws Exception{
        log.info("{}.", threadCount);
        Thread.sleep(1000);
    }
}
