package com.bravedawn.concurrency.example.volatile_;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-04-03 16:53
 *
 * volatile关键字并不能保证程序的原子性
 */
public class VolatileExample implements Runnable{

    public static volatile int inc = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            inc++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
       Thread[] threads = new Thread[10];

       for (int i = 0; i < 10; i++) {
           threads[i] = new Thread(new VolatileExample());
           threads[i].start();
       }

       for (int i = 0; i < 10; i++) {
           threads[i].join();
       }

        System.out.println(inc);
    }
}
