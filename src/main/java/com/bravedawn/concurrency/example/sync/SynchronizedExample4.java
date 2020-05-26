package com.bravedawn.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 冯晓 on 2019/3/3.
 */
@Slf4j
public class SynchronizedExample4 extends SynchronizedExample1{

    // 修饰一个方法：同步方法
    public void test2(int j){
        for (int i = 0; i < 10; i++){
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample4 example1 = new SynchronizedExample4();

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 同一对象的两个进程同时调用test2时，因为没有加synchronized关键字，所以不是同步的
        executorService.execute(() -> {
            example1.test2(1);
        });

        executorService.execute(() -> {
            example1.test2(2);
        });
    }
}
