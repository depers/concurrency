package com.bravedawn.concurrency.example.currentlimiting;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-12-13 10:22
 */

@Slf4j
public class RateLimiterExample2 {


    /**
     * 每秒钟放入5个令牌，相当于每秒只允许执行5个请求
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(5);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 从令牌桶中获取一个令牌，若没有获取到会阻塞直到获取到为止，所以所有的请求都会被执行
            RATE_LIMITER.acquire();
            // 获取成功，执行相应逻辑
            handle(i);
        }
    }

    private static void handle(int i) {
        log.info("{}", i);
    }
}
