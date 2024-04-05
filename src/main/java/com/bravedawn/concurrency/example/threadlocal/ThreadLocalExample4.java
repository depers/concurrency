package com.bravedawn.concurrency.example.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/24 16:49
 */

@Slf4j
public class ThreadLocalExample4 {


    // 将threadLocal作为强引用使用
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    // -Xms200m -Xmx200m
    public static void main(String[] args) {
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                try {
                    func();

                    // System.gc();
                    Thread t = Thread.currentThread();
                    System.out.println(t);
                } catch (InterruptedException e) {
                    log.error("异常", e);
                }

            }
        });
    }


    private static void func() throws InterruptedException {
        // 设置value值为10m大的数组
        threadLocal.set("helloWorld");
    }
}
