package com.bravedawn.concurrency.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception{

        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable.");
                Thread.sleep(5000);
                return "OK";
            }
        });

        new Thread(futureTask).start();
        log.info("do something in main.");
        Thread.sleep(1000);
        String result = futureTask.get();  // 此处会阻塞5秒钟
        log.info("result: {}.", result);
    }
}
