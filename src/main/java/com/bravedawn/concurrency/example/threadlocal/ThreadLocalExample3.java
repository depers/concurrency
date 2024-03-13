package com.bravedawn.concurrency.example.threadlocal;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-03-13 16:49
 */
public class ThreadLocalExample3 {


    /**
     * -Xmx100m -Xms100m
     *
     * 创建了一个主线程的ThreadLocal，我将threadlocal和value都置为null，手动在jvisualvm中触发gc并没有减少堆栈内存
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);

        Byte[] array = new Byte[1024 * 1024 * 20];
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(array);

        // 移除
        threadLocal.remove();

        Thread.sleep(100000);
    }
}
