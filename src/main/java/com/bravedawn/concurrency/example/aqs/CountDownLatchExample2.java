package com.bravedawn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {

    private static final int threadCount = 200;

    public static void main(String[] args) throws Exception{

        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute(() -> {
                try{
                    test(threadNum);
                } catch (Exception e){
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(10, TimeUnit.MILLISECONDS); // 等待10ms，如果执行不完，提前结束
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadCount) throws Exception{
        Thread.sleep(100);
        log.info("{}.", threadCount);
    }
}
