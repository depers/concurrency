package com.bravedawn.concurrency.example.currentlimiting;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/1 13:22
 *
 * 令牌算法
 */
public class TokenBucketRateLimiter {

    private long capacity;
    private long tokenCount;
    private long rate;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public TokenBucketRateLimiter(long capacity, long rate) {
        this.capacity = capacity;
        this.rate = rate;
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduleRefill(); // 启动令牌补充任务
    }

    private void scheduleRefill() {
        synchronized (this) {
            scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
                tokenCount = Math.min(capacity, tokenCount + rate);
            }, 1, 2, TimeUnit.SECONDS);
        }
    }


    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        if (tokenCount > 0) {
            tokenCount--;
            return true;
        } else {
            return false;
        }
    }
}
