package com.bravedawn.concurrency.example.currentlimiting;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/1 13:09
 *
 * 漏斗桶算法
 */
public class LeakyBucketRateLimiter {

    private long capacity; // 漏桶容量，即最大允许的请求数量 
    private long rate;  // 漏水速率，即每秒允许通过的请求数量
    private long water; // 漏桶当前水量
    private long lastTime; // 上一次请求通过的时间戳

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
