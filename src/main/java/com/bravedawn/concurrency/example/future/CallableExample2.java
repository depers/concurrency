package com.bravedawn.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/11 10:11
 *
 * Callable会将异常抛给上一级去处理
 */
@Slf4j
public class CallableExample2 {


    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something in callable.");
            int i = 1/0;
            return "OK";
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        try {
            String str = future.get();
            log.info(str);
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        executorService.shutdown();

    }
}
