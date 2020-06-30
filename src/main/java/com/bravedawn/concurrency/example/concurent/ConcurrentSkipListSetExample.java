package com.bravedawn.concurrency.example.concurent;

import com.bravedawn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author 冯晓
 * @Date 2020/6/3 21:30
 */
@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetExample {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static Set<Integer> set = new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            final int count = i;
            executorService.execute(()->{
                try{
                    semaphore.acquire();  //阻塞add()
                    update(count);
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();  //直到countDown减为0
        executorService.shutdown();
        log.info("size: {}.", set.size());
    }

    private static void update(int i){
        set.add(i);
    }
}
