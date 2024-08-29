package com.bravedawn.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/8/28 21:43
 */
@Slf4j
public class FutureTaskExample3 {


    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        FutureTask<Object> future = (FutureTask<Object>) executor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                return 1 / 0;
            }
        });

        try {
            future.get();
        } catch (Throwable e) {
            log.error("出现异常", e);
        }

        log.info("线程池执行结束");


    }
}
