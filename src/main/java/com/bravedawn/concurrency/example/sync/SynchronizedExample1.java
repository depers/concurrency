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
    public void test1(){
        synchronized (this){
            for (int i = 0; i < 10; i++){
                log.info("test1 - {}", i);
            }
        }
    }

    // 修饰一个方法：同步方法
    public synchronized void test2(){
        for (int i = 0; i < 10; i++){
            log.info("test2 - {}", i);
        }
    }


    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        example1.test1();
        example1.test1();

        /*
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 使用线程池的目的是为了查看同一对象的两个进程同时调用test1时的情况
        executorService.execute(() -> {
            example1.test2();
        });

        executorService.execute(() -> {
            example1.test2();
        });
        */
    }




}
