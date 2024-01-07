package com.bravedawn.concurrency.example.currentlimiting;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-12-13 10:20
 */

@Slf4j
public class RateLimiterExample1 {


    /**
     * 每秒钟放入5个令牌，相当于每秒只允许执行5个请求
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(5);

    public static void main(String[] args) {
        // 模拟有100个请求
        for (int i = 0; i < 100; i++) {
            // 尝试从令牌桶中获取令牌，若获取不到则等待300毫秒看能不能获取到
            if (RATE_LIMITER.tryAcquire(300, TimeUnit.MILLISECONDS)) {
                // 获取成功，执行相应逻辑
                handle(i);
            }
        }
    }

    private static void handle(int i) {
        log.info("{}", i);
    }
}
