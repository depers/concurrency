package com.bravedawn.concurrency.example.lock;

import com.bravedawn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReenTrantLock的简单使用
 */

@Slf4j
@ThreadSafe
public class LockExample2 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    private static Lock lock = new ReentrantLock();

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
        log.info("count:{}", count);
    }

    private static void add(){
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }

    }
}
