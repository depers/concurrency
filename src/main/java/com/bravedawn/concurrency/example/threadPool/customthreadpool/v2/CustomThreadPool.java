package com.bravedawn.concurrency.example.threadPool.customthreadpool.v2;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-23 09:53
 */

@Slf4j
public class CustomThreadPool extends ThreadPoolExecutor {


    private final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public static void main(String[] args) {
        CustomThreadPool example = new CustomThreadPool(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("custom"), new AbortPolicy());

        example.execute(CustomThreadPool::operate);

        example.shutdown();
    }

    private static void operate() {
        log.info("开始执行");
        int i = 1 / 0;
        log.info("执行结束");
    }

}
