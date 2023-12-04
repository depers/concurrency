package com.bravedawn.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-30 10:15
 */

@Slf4j
public class ThreadExample6 extends Thread {

    /**
     * 中断线程，方法二：设置标志位
     */

    // 注意到标志位boolean running是一个线程间共享的变量。线程间共享变量需要使用volatile关键字标记，确保每个线程都能读取到更新后的变量值。
    public volatile boolean running = true;

    @Override
    public void run() {
        int n = 0;
        while (running) {
            n ++;
            log.info(n + " hello!");
        }
        log.info("end!");
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadExample6 t = new ThreadExample6();
        t.start();
        Thread.sleep(20);
        t.running = false;  // 设置标识位置为false，中断线程
    }
}
