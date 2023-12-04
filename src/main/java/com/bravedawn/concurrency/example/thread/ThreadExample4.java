package com.bravedawn.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-29 17:22
 */

@Slf4j
public class ThreadExample4 extends Thread {

    /**
     * 中断线程，方法一：显示调用线程的interrupt()方法中断线程，也就是让线程终止执行，如果目标线程通过检测isInterrupted()标志获取自身是否已中断。
     *
     * 下面的代码 main线程通过调用t.interrupt()方法中断t线程，但是要注意，interrupt()方法仅仅向t线程发出了“中断请求”，至于t线程是否能立刻响应，
     * 要看具体代码。而t线程的while循环会检测isInterrupted()，所以上述代码能正确响应interrupt()请求，使得自身立刻结束运行run()方法。
     */

    @Override
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            n++;
            log.info("[{}] - hello!", n);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t = new ThreadExample4();
        t.start();
        Thread.sleep(1); // 暂停1ms
        t.interrupt(); // 中断线程
        t.join(); // 主线程等待t线程执行结束
        log.info("end");
    }
}
