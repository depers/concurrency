package com.bravedawn.concurrency.example.thread;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-01-12 15:40
 */
public class ThreadExample9 {

    /**
     * 线程同步
     * 在下面的这段代码中，使用synchronized关键字对共享变量的读写进行了同步控制
     *
     * 这里还有一个点就是如何判断这个变量是否存在线程同步的问题
     * 1.方法中的局部变量是线程安全的，因为每个线程在执行方法时都有局部变量的副本。
     * 2.基本类型和引用类型的赋值是原子操作，不需要同步。
     *      基本类型（long和double除外）赋值，例如：int n = m；
     *      引用类型赋值，例如：List<String> list = anotherList。
     * 3.不可变对象无需同步。
     */

    public static void main(String[] args) throws Exception {
        AddThread add = new AddThread();
        DecThread dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }
}

class Counter2 {
    // 用来加锁的共享实例
    public static final Object lock = new Object();

    public static int count = 0;
}


class AddThread2 extends Thread {

    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter2.lock) {
                Counter.count += 1;
            } // 不管花括号内是否会抛出异常，都会在synchronized结束处正确释放锁
        }
    }
}


class DecThread2 extends Thread {

    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter2.lock) {
                Counter.count -= 1;
            }
        }
    }
}
