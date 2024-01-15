package com.bravedawn.concurrency.example.thread;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-01-12 16:25
 */
public class CounterExample2 {

    private int value;
    private int another;
    private Object lockA = new Object();
    private Object lockB = new Object();

    public void add(int m) {
        synchronized (lockA) { // 获得lockA的锁
            this.value += m;
            synchronized (lockB) { // 获得lockB的锁
                this.another += m;
            } // 释放lockB的锁
        } // 释放lockA的锁
    }

    public void dec(int m) {
        synchronized (lockB) { // 获得lockB的锁
            this.another -= m;
            synchronized (lockA) { // 获得lockA的锁
                this.value -= m;
            } // 释放lockA的锁
        } // 释放lockB的锁
    }
}
