package com.bravedawn.concurrency.example.count;

import com.bravedawn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadSafe
public class CountExample2 {

    /**
     * 使用AtomicInteger实现计数的原子操作
     * 其中incrementAndGet()和getAndIncrement()执行的逻辑是相同的，但是返回的值不一样，incrementAndGet()返回的是加1之后的值，getAndIncrement()返回的是加1之前的旧值
     */

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            executorService.execute(()->{
                try{
                    semaphore.acquire();  //阻塞add()
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();  //直到countDown减为0
        executorService.shutdown();
        log.info("count:{}", count.get());
    }

    private static void add(){
        count.incrementAndGet();
        //count.getAndIncrement();
    }
}
