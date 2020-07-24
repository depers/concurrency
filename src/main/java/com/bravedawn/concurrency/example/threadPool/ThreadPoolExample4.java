package com.bravedawn.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        /**
         * 延时3秒执行
         */
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("schedule run 1");
            }
        }, 3, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
