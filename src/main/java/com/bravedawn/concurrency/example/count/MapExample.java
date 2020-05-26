package com.bravedawn.concurrency.example.count;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author 冯晓
 * @Date 2020/3/6 17:12
 */
@Slf4j
public class MapExample {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    //public static int threadTotal = 200;
    public static int threadTotal = 1;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);

        for (int i = 0; i < clientTotal; i++){
            final int threadNum = i;
            executorService.execute(()->{
                try{
                    semaphore.acquire();  //阻塞add()
                    func(threadNum);
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception: ", e);
                }
            });
        }
        executorService.shutdown();
        log.info("size:{}", map.size());
    }

    private static void func(int threadNum){
        map.put(threadNum, threadNum);
    }
}
