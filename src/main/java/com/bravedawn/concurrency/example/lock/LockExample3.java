package com.bravedawn.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的应用
 */

@Slf4j
public class LockExample3 {

    private int data;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void readData() {
        lock.readLock().lock();
        try {
            // 读取数据
            log.info("读取数据：" + data);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void writeData(int value) {
        lock.writeLock().lock();
        try {
            // 写入数据
            data = value;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        LockExample3 example = new LockExample3();

        // 创建多个线程进行读操作
        for (int i = 0; i < 5; i++) {
            new Thread(() -> example.readData()).start();
        }

        // 创建一个线程进行写操作
        new Thread(() -> example.writeData(100)).start();
    }

}
