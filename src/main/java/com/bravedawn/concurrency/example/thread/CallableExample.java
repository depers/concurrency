package com.bravedawn.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-29 16:57
 */

@Slf4j
public class CallableExample implements Callable<String> {

    /**
     * 创建线程的方法三：通过实现Callable接口，通过线程池实现
     */

    @Override
    public String call() throws Exception {
        log.info("我是一个异步线程执行后的返回值");
        return "我是一个异步线程执行后的返回值";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> submit = executorService.submit(new CallableExample());
        String result = submit.get();
        log.info("异步线程的执行结果：{}", result);

    }
}
