package com.bravedawn.concurrency.example.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-03-13 16:49
 */
@Slf4j
public class ThreadLocalExample2 {


    /**
     * -Xmx100m -Xms100m -Xlog:gc+heap=trace:stdout:time
     * 这里我新建一个线程去异步创建ThreadLocal，当线程执行完成之后，我手动触发GC，是可以对这块内存进行回收的。
     * 也就是说异步执行的线程中如果使用了ThreadLocal，当线程执行完后，触发gc就会对其内存进行回收
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal threadLocal = new ThreadLocal();

        Thread t = new Thread(() -> {
            log.info("异步线程开始执行");
            try {
                log.info("开始睡眠");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("结束睡眠");

            Byte[] array = new Byte[1024 * 1024 * 20];
            threadLocal.set(array);
            log.info("异步线程执行结束");
        });

        t.start();
        while (t.isAlive()) {
            log.info("异步线程的状态={}", t.getState());
            Thread.sleep(1000);
        }


        if (!t.isAlive()) {
            log.info("线程死了");
            System.gc();
        }

        Thread.sleep(5000);
    }
}
