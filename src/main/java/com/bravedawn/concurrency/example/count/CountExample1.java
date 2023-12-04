package com.bravedawn.concurrency.example.count;

import com.bravedawn.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class CountExample1 {

    /**
     * 在多线程情况下，int是线程不安全的，因为这里没有做工作内存和主内存的比较工作，多个线程共享变量count，A和B线程同时加1之后count为1，完成计数之后将工作内存的count值
     * 同步到主内存，按照先后此时计数应该是2才对，所以这里因为内存模型的原因会导致线程不安全
     */

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

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
        count++;
    }
}
