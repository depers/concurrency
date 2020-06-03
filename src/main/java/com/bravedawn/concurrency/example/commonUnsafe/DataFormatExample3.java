package com.bravedawn.concurrency.example.commonUnsafe;

import com.bravedawn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author 冯晓
 * @Date 2020/6/3 21:13
 */
@Slf4j
@ThreadSafe
public class DataFormatExample3 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

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
    }

    private static void update(int count){
        log.info("{}, {}.", count, DateTime.parse("20200603", dateTimeFormatter).toDate());
    }
}
