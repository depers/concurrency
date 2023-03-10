package com.bravedawn.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 冯晓 on 2019/3/3.
 */
@Slf4j
public class SynchronizedExample1 {

    // 修饰一个代码块：同步语句块
    public void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10; i++){
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    // 修饰一个方法：同步方法
    public synchronized void test2(int j){
        for (int i = 0; i < 10; i++){
            log.info("test2 {} - {}", j, i);
        }
    }


    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 使用线程池的目的是为了查看同一对象的两个进程同时调用test1时的情况，第一遍计数做完才能做第二遍
        /**
        executorService.execute(() -> {
            example1.test2(1);
        });

        executorService.execute(() -> {
            example1.test2(2);
        });
        */

        // 不同对象调用同步方法是互不影响的
        executorService.execute(() -> {
            example1.test2(1);
        });

        executorService.execute(() -> {
            example2.test2(2);
        });
    }
}
