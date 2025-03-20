package com.bravedawn.concurrency.example.parallelstream;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description : TODO
 * @Author : depers
 * @Project : concurrency
 * @Date : Created in 2025-03-18 16:53
 */
@Slf4j
public class ParallelStreamExample {

    /**
     * 演示并行流的执行
     */

    public static void main(String[] args) {
        int coreCount = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(coreCount, coreCount, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        List<Integer> futureResult = Collections.synchronizedList(new ArrayList<>(10));
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> task = threadPoolExecutor.submit(() -> {
                log.info("开始执行业务逻辑");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Random random = new Random(System.currentTimeMillis());
                return random.nextInt();
            });
            futureList.add(task);
        }

        futureList.parallelStream().forEach(future -> {
            try {
                int result = future.get();
                log.info("获取执行结果: {}", result);
                futureResult.add(result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        log.info("结果list：{}", futureResult);





    }
}
