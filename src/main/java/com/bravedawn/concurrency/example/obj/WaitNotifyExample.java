package com.bravedawn.concurrency.example.obj;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-12-19 16:16
 *
 * 测试wait()和notify()方法
 *
 * wait(): 调用该方法的线程进入WAITING状态，只有等待另外线程的通知或被中断才会返回，需要注意，调用wait()方法后，会释放对象的锁。
 * notify(): 通知一个在对象上等待的线程，使其从wait()返回，而返回的前提是该线程获取到了对象的锁。
 */


@Slf4j
public class WaitNotifyExample {


    public static void main(String[] args) {

        Object lock = new Object();


        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("线程A等待获取lock锁");
                synchronized (lock) {
                    try {
                        log.info("线程A获取了lock锁");
                        Thread.sleep(1000);
                        log.info("线程A将要运行lock.wait()方法进行等待");
                        lock.wait();
                        log.info("线程A等待结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("线程B等待获取lock锁");
                synchronized (lock) {
                    log.info("线程B获取了lock锁");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("线程B将要运行lock.notify()方法进行通知");
                    lock.notify();
                }
            }
        }).start();
    }
}
