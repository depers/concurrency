package com.bravedawn.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();  // 线程1加入了AQS等待队列中
                log.info("wait signal"); // 1
                condition.await();  // 线程1从AQS等待队列中移除，对应操作是锁的释放，此时他会加入到Condition的等待队列中，线程开始等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock(); // 线程1是释放锁
        }).start();

        new Thread(() -> {
            reentrantLock.lock();  // 线程2因为线程1释放锁的缘故，获取到了锁，线程2加入AQS的等待队列中
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();  // 线程2调用发送信号的方法，此时Condition的等待队列中线程1被取出放到AQS的队列中，此时线程1并没有被唤醒
            log.info("send signal ~ "); // 3
            reentrantLock.unlock(); // 线程2释放锁，唤醒AQS队列中的线程1
        }).start();
    }
}
