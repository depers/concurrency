package com.bravedawn.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-29 16:54
 */

@Slf4j
public class ThreadExample2 implements Runnable{

    /**
     * 创建线程的方法二：创建Thread实例时，传入一个Runnable实例
     */


    @Override
    public void run() {
        log.info("我是一个新的线程");
    }


    public static void main(String[] args) {
        log.info("我是主线程");
        ThreadExample2 e = new ThreadExample2();
        Thread t = new Thread(e);
        t.start();
    }
}
