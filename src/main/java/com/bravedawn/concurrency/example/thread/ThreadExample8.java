package com.bravedawn.concurrency.example.thread;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-01-12 15:10
 */
public class ThreadExample8 {


    /**
     * 线程同步
     * 多线程同时读写共享变量时，可能会造成逻辑错误，因此需要通过synchronized同步；
     * 运行下面的这段代码，你就会发现控制台每次输出的结果是不一样的。原因是对于Counter.count共享变量没有做线程同步的控制；
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

class Counter {
    public static int count = 0;
}

class AddThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Counter.count += 1;
        }
    }
}

class DecThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Counter.count -= 1;
        }
    }
}
