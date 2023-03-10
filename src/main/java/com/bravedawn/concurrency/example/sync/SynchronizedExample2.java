package com.bravedawn.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 冯晓 on 2019/3/3.
 */
@Slf4j
public class SynchronizedExample2 {

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
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        example1.test2(1);
        example2.test2(2);
        /*
        // 两个对象方法交叉顺序执行
        ExecutorService executorService = Executors.newCachedThreadPool();


        executorService.execute(() -> {
            example1.test2(1);
        });

        executorService.execute(() -> {
            example2.test2(2);
        });
        */
    }




}
