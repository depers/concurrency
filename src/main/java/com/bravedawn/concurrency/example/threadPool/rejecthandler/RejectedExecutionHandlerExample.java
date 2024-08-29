package com.bravedawn.concurrency.example.threadPool.rejecthandler;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/8/28 22:00
 */

@Slf4j
public class RejectedExecutionHandlerExample {

    /**
     * 配置JVM参数：-Xmx10m -Xms10m
     */

    private static AtomicInteger atomicIntegerExecute = new AtomicInteger(0);
    private static AtomicInteger atomicIntegerNoExecute = new AtomicInteger(0);

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5),
            Executors.defaultThreadFactory(), new MyRejectedExecutionHandler());

    static CountDownLatch countDownLatch = new CountDownLatch(10000);


    private static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            countDownLatch.countDown();
            log.error("线程池执行超过队列长度, count={}, countDownLatch={}", atomicIntegerNoExecute.incrementAndGet(), countDownLatch.getCount());
        }
    }

    static int getRandomNumber(int min, int max) {
        Random random = new Random();
        // nextInt(n)，生成一个在0(包括)到n(不包括)之间的一个数，带参数的方法中，n必须大于0，否则就会报IllegalArgumentException
        int randomWithNextIntWithARange = random.nextInt(max - min) + min;
        return randomWithNextIntWithARange;
    }


    public static void main(String[] args) throws InterruptedException {
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            Future<Object> future = executor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    try {
                        log.info("线程池开始执行代码逻辑, count={}", atomicIntegerExecute.incrementAndGet());
                        int a = getRandomNumber(0, 10);
                        return 100 / a;
                    } catch (Throwable e) {
                        log.error("异步执行出现异常", e);
                    } finally {
                        countDownLatch.countDown();
                        log.info("正常执行, countDownLatch={}, taskCount={}", countDownLatch.getCount(), executor.getTaskCount());
                    }
                    return null;
                }
            });

            futures.add(future);
        }

        log.info("异步任务的大小：{}", futures.size());

        for (Future<?> future : futures) {
            try {
                log.info("线程池的执行结果是：{}", future.get());
            } catch (Throwable e) {
                log.error("多线程运行出现异常", e);
            }

        }


        countDownLatch.await();
        log.info("执行结束");
        executor.shutdown();
        log.info("最后的执行情况, executeCount={}, noExecuteCount={}", atomicIntegerExecute.get(), atomicIntegerNoExecute.get());

    }
}
