package com.bravedawn.concurrency.example.threadPool.customthreadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-23 14:09
 */
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
        this.threadNum.increment();
        return t;
    }
}
