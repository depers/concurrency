package com.bravedawn.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-29 16:51
 */

@Slf4j
public class ThreadExample extends Thread{

    /**
     * 创建线程的方法一：从Thread派生一个自定义类，然后覆写run()方法：
     */


    @Override
    public void run() {
        log.info("我是一个新的线程");
    }


    public static void main(String[] args) {
        log.info("我是主线程");
        ThreadExample example = new ThreadExample();
        example.start();
    }
}
