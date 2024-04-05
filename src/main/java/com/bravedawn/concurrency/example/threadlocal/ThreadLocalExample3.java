package com.bravedawn.concurrency.example.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/17 22:38
 *
 * 演示
 */

@Slf4j
public class ThreadLocalExample3 {


    public static void main(String[] args) throws InterruptedException {
        func();

        System.gc(); // 手动触发垃圾回收
        Thread.sleep(100);

        Thread t = Thread.currentThread();  // 第二个断点
        Thread.sleep(10000);
    }

    private static void func() {
        ThreadLocal<String> threadLocal = new ThreadLocal();
        threadLocal.set("dev_fengxiao@163.com");
        log.info("threadLocal参数设置完成");
        Thread t = Thread.currentThread(); // 第一个断点
    }
}
