package com.bravedawn.concurrency.example.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/1 15:51
 */
public class AbaProblem {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1: Reading value = " + atomicInteger.get());
            atomicInteger.incrementAndGet();
            System.out.println("Thread 1: Writing value = " + atomicInteger.get());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2: Reading value = " + atomicInteger.get());
            atomicInteger.decrementAndGet();
            System.out.println("Thread 2: Writing value = " + atomicInteger.get());
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
