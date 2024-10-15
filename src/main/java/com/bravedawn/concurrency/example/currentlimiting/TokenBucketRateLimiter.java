package com.bravedawn.concurrency.example.currentlimiting;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/1 13:22
 *
 * 令牌桶算法
 */
public class TokenBucketRateLimiter {

    private long capacity; // 令牌桶容量，即最大允许的请求数量
    private long tokenCount; // 当前令牌数量 
    private long rate; // 令牌产生速率，即每秒产生的令牌数量
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor; // 调度器

    public TokenBucketRateLimiter(long capacity, long rate) {
        this.capacity = capacity;
        this.rate = rate;
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduleRefill(); // 启动令牌补充任务
    }

    private void scheduleRefill() {
        synchronized (this) {
            scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
                // 补充令牌，但不超过容量
                tokenCount = Math.min(capacity, tokenCount + rate);
                // 每秒产生一次令牌
            }, 1, 1, TimeUnit.SECONDS);
        }
    }


    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        // 判断令牌数量是否大于0
        if (tokenCount > 0) {
            tokenCount--;
            return true;
        } else {
            return false;
        }
    }
}
