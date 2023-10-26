package com.bravedawn.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * @Description : TODO
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-10-20 16:37
 */
public class MultiTaskExecExample {


    private final static Logger log = LoggerFactory.getLogger(MultiTaskExecExample.class);

    private ExecutorService executor;


    public MultiTaskExecExample () {
        executor = Executors.newFixedThreadPool(5);
    }


    public <T> List<Boolean> exec(T args, Function<T, Boolean>... functions) throws InterruptedException {
        log.info("多任务执行开始");
        CountDownLatch countDownLatch = new CountDownLatch(functions.length);
        List<Boolean> resultList = new ArrayList<>();

        for (int i = 0; i < functions.length; i++) {
            int index = i;
            executor.execute(() -> {
                Boolean result = false;
                try {
                    result = functions[index].apply(args);
                } catch (Throwable e) {
                    log.error("多任务执行异常", e);
                } finally {
                    countDownLatch.countDown();
                }
                resultList.add(result);
            });
        }
        countDownLatch.await();
        executor.shutdown();
        log.info("多任务执行完成");
        return resultList;
    }


    public static void main(String[] args) throws InterruptedException {
        Function<String, Boolean> func1 = (String s) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("报文内容是：{}", s);
            return true;
        };

        Function<String, Boolean> func2 = (String s) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("报文内容是：{}", s);
            return true;
        };

        MultiTaskExecExample taskExecExample = new MultiTaskExecExample();
        List<Boolean> result = taskExecExample.exec("中国", func1, func2);
        System.out.println(result);

    }



}
