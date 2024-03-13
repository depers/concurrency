package com.bravedawn.concurrency.example.currentlimiting;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/1 13:09
 *
 * 漏斗桶算法
 */
public class LeakyBucketRateLimiter {

    private long capacity;
    private long rate;
    private long water;
    private long lastTime;

    public LeakyBucketRateLimiter(long capacity, long rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.water = 0;
        this.lastTime = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire() {

        long currentTime = System.currentTimeMillis();

        water = Math.max(0, water - (currentTime - lastTime) / 1000 * rate);

        if (water < capacity) {
            water++;
            lastTime = currentTime;
            return true;
        }
        return false;
    }
}
