package com.bravedawn.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-30 09:53
 */

@Slf4j
public class ThreadExample5 extends Thread {

    /**
     * 中断线程，捕获中断异常，如果目标线程出于等待状态，中断该线程，该线程会捕获到InterruptedException
     *
     * main线程通过调用t.interrupt()从而通知t线程中断，而此时t线程正位于hello.join()的等待中，此方法会立刻结束等待并抛出InterruptedException。
     * 由于我们在t线程中捕获了InterruptedException，因此，就可以准备结束该线程。在t线程结束前，对hello线程也进行了interrupt()调用通知其中断。
     * 如果去掉hello.interrupt();这一行代码，可以发现hello线程仍然会继续运行，且JVM不会退出。
     */


    public static void main(String[] args) throws InterruptedException {
        ThreadExample5 t = new ThreadExample5();
        t.start();
        Thread.sleep(1000);
        t.interrupt();
        t.join();
        log.info("end");
    }


    @Override
    public void run() {
        Thread hello = new HelloThread();
        hello.start(); // 启动hello线程
        try {
            hello.join(); // 等待hello线程执行结束
        } catch (InterruptedException e) {
            log.info("interrupted");
        }

        hello.interrupt();
    }


    private class HelloThread extends Thread {

        public void run() {
            int n = 0;
            while (!isInterrupted()) {
                n++;
                log.info(n + " hello!");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
