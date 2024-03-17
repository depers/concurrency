package com.bravedawn.concurrency.example.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/17 22:38
 */

@Slf4j
public class ThreadLocalExample3 {


    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            ThreadLocal<String> threadLocal = new ThreadLocal();
            threadLocal.set("dev_fengxiao@163.com");
            log.info("--threadLocal参数设置完成");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread());
        });


        t.start();

        System.gc();
        Thread.sleep(1000);

        System.out.println(t);

        Thread.sleep(100000);
    }
}
