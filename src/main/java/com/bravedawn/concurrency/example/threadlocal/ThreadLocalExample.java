package com.bravedawn.concurrency.example.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-03-13 16:49
 */
@Slf4j
public class ThreadLocalExample {


    /**
     * -Xmx100m -Xms100m -Xlog:gc+heap=trace:stdout:time
     *
     * 创建了一个主线程的ThreadLocal，我将threadlocal和value都置为null，手动在jvisualvm中触发gc并没有减少堆栈内存
     * 因为主线程还没有被终止，所以线程所持有的ThreadLocalMap还不能被清理
     */
    public static void main(String[] args) throws InterruptedException {

        // 创建一个20m的数据
        Byte[] array = new Byte[1024 * 1024 * 20];
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(array);

        // 将强引用变量置为nul
        threadLocal = null;
        array = null;

        log.info("执行gc");
        System.gc();

        if (Thread.currentThread().isAlive()) {
            log.info("异步线程的状态={}", Thread.currentThread().getState());
        }

        // 保持主线程
        Thread.sleep(10000);
    }
}
