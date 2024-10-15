package com.bravedawn.concurrency.example.currentlimiting;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/1 12:21
 *
 * 固定窗口算法（计数器法）
 */
public class FixedWindowRateLimiter {

    private long windowsTime; // 定义窗口的时间范围，可以是1000ms
    private long windowsCount; // 定义窗口阀值，可以使100，也就是说1s可以接受100个请求
    private long counter; // 计数器
    private long lastTime; // 上一次请求的时间


    public FixedWindowRateLimiter(int windowsTime, int windowsCount) {
        this.windowsTime = windowsTime;
        this.windowsCount = windowsCount;
        this.counter = 0;
        this.lastTime = System.currentTimeMillis();
    }


    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > windowsTime) {
            counter = 0;
            lastTime = System.currentTimeMillis();
        }

        if (counter < windowsCount) {
            counter++;
            return true;
        }

        return false;
    }
}
