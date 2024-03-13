package com.bravedawn.concurrency.example.threadPool.customthreadpool.v2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-23 14:09
 */

@Slf4j
public class CustomThreadFactory implements ThreadFactory {

    private String namePrefix;
    private LongAdder threadNum = new LongAdder();


    public CustomThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(this.namePrefix + "-" + this.threadNum);
        // 处理线程执行逻辑抛出异常不输出日志到文件的问题
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.error("线程池线程执行出现异常", e);
            }
        });
        this.threadNum.increment();
        return t;
    }
}
