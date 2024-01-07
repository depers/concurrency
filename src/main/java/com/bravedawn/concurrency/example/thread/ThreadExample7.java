package com.bravedawn.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-12-06 10:08
 */

@Slf4j
public class ThreadExample7 {

    /**
     * Thread.yield()执行案例
     */

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                log.info("Thread={}, i={}", Thread.currentThread().getName(), i);

                // 当i=6时，执行这段代码的线程让出CPU时间片
                if (i % 6 == 0) {
                    Thread.yield();
                }
            }
        };


        Thread t1 = new Thread(runnable, "张三");
        Thread t2 = new Thread(runnable, "李四");
        t1.start();
        t2.start();
    }
}
