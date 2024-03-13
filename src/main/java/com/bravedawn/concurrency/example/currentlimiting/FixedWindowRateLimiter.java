package com.bravedawn.concurrency.example.currentlimiting;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/1 12:21
 *
 * 固定窗口算法（计数器法）
 */
public class FixedWindowRateLimiter {

    private long windowsTime;
    private long windowsCount;
    private long counter;
    private long lastTime;


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
