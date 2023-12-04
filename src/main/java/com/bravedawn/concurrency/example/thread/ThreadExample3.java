package com.bravedawn.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-29 17:12
 */

@Slf4j
public class ThreadExample3 {

    /**
     * 一个线程还可以等待另一个线程直到其运行结束
     *
     * 当main线程对线程对象t调用join()方法时，主线程将等待变量t表示的线程运行结束，即join就是指等待该线程结束，然后才继续往下执行自身线程。
     */


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            log.info("新启线程执行");
        });

        log.info("start");
        t.start();
        t.join();
        log.info("end");
    }
}
