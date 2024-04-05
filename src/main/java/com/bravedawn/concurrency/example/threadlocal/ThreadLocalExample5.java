package com.bravedawn.concurrency.example.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/24 16:49
 */

@Slf4j
public class ThreadLocalExample5 {


    // -Xms200m -Xmx200m
    public static void main(String[] args) {

        Executors.newSingleThreadExecutor().execute(() -> {
            int i = 0;
            while (true) {
                try {
                    func();

                    if (i % 10 == 0) {
                        System.gc();
                    }

                    Thread t = Thread.currentThread();
                    System.out.println(t);
                } catch (InterruptedException e) {
                    log.error("异常", e);
                }

                i++;
            }
        });
    }

    private static void func() throws InterruptedException {
        // threadLocal作为局部变量
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        // 设置value值
        threadLocal.set("helloWorld");
    }
}
