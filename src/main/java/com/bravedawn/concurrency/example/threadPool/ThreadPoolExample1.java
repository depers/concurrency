package com.bravedawn.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExample1 {

    public static void main(String[] args) {

        /**
         * 创建10个线程去执行
         */
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}.", index);
                }
            });
        }
        executorService.shutdown();
    }
}
