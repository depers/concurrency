package com.bravedawn.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 冯晓 on 2019/3/3.
 */
@Slf4j
public class SynchronizedExample3 {

    // 修饰一个类：两个对象进程顺序执行
    public void test1(int j){
        synchronized (SynchronizedExample3.class){
            for (int i = 0; i < 10; i++){
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    // 修饰一个静态方法：两个对象进程顺序执行
    public synchronized static void test2(int j){
        for (int i = 0; i < 10; i++){
            log.info("test2 {} - {}", j, i);
        }
    }


    public static void main(String[] args) {
        SynchronizedExample3 example1 = new SynchronizedExample3();
        SynchronizedExample3 example2 = new SynchronizedExample3();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            //example1.test1(1);
            example1.test2(1);
        });

        executorService.execute(() -> {
            //example2.test1(2);
            example2.test2(2);
        });

    }




}
