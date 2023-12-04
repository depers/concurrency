package com.bravedawn.concurrency.example.threadPool.customthreadpool;

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

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.info("异步线程开始执行，threadId={}", t.getName());
        Map<String, Object> threadMap = new HashMap<>();
        threadMap.put("startTime", System.currentTimeMillis());
        threadMap.put("threadId", t.getName());
        threadLocal.set(threadMap);
    }


    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        Map<String, Object> map = threadLocal.get();
        long startTime = (long) map.get("startTime");
        String threadId = (String) map.get("threadId");
        long endTime = System.currentTimeMillis();
        log.info("异步线程执行结束，threadId={}, 耗时={}ms", threadId, endTime - startTime);
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
