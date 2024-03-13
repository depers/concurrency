package com.bravedawn.concurrency.example.currentlimiting;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/3/1 12:30
 *
 * 滑动窗口算法
 */
public class SlidingWindowRateLimiter {

    private Queue<Long> timestamps; // 存储请求的时间戳队列
    private long windowSize;    // 窗口大小，即时间窗口内允许的请求数量
    private long windowDuration;    // 窗口持续时间，单位：毫秒

    public SlidingWindowRateLimiter(long windowSize, long windowDuration) {
        this.windowSize = windowSize;
        this.windowDuration = windowDuration;
        this.timestamps = new LinkedList<>();
    }

    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();

        // 删除超过滑动窗口的请求
        while (!timestamps.isEmpty() && currentTime - timestamps.peek() > windowDuration) {
            timestamps.poll();
        }

        // 如果没有达到分发请求的数量，分发请求
        if (timestamps.size() < windowSize) {
            timestamps.offer(System.currentTimeMillis());
            return true;
        }

        return false;

    }

}
