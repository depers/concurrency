package com.bravedawn.concurrency.example.threadPool.customthreadpool.v1;

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

    // 统计线程执行耗时
    private final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        threadLocal.set(System.currentTimeMillis());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Long start = threadLocal.get();
        threadLocal.remove();
        long thisTime = System.currentTimeMillis() - start;
        log.info("任务耗时[{}]ms", thisTime);

    }


    public static void main(String[] args) {
        CustomThreadPool example2 = new CustomThreadPool(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("custom"), new AbortPolicy());

        for (int i = 0; i < 1000; i++) {
            example2.execute(() -> {
                operate();
            });
        }


        example2.shutdown();
    }

    private static void operate() {
        try {
            Thread.sleep(5000);
            log.info("执行完毕");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
