package com.bravedawn.concurrency.example.threadlocal;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-03-13 16:49
 */
public class ThreadLocalExample2 {


    /**
     * -Xmx=100m -Xms=100m
     * 这里我新建一个线程去异步创建ThreadLocal，当线程执行完成之后，我手动触发GC，是可以对这块内存进行回收的。
     * 也就是说异步执行的线程中如果使用了ThreadLocal，当线程执行完后，触发gc就会对其内存进行回收
     */
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Byte[] array = new Byte[1024 * 1024 * 20];
            ThreadLocal threadLocal = new ThreadLocal();
            threadLocal.set(array);
        });

        t.start();

        Thread.sleep(100000);
    }
}
