package com.bravedawn.concurrency.example.thread;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-01-12 16:30
 */
public class ThreadExample10 {

    /**
     * synchronized关键字锁住的对象时this，也就是当前实例，创建多个CounterExample实例的时候，它们之间互不影响，可以并发执行。
     */

    private int count = 0;

    public void add(int n) {
        synchronized (this) {
            count += n;
        }
    }

    public void dec(int n) {
        synchronized (this) {
            count -= n;
        }
    }

    public int get() {
        return count;
    }


}
