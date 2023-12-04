package com.bravedawn.concurrency.example.atomic;

import com.bravedawn.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        boolean b1 = count.compareAndSet(0, 2); // 2
        log.info("result={}", b1);
        boolean b2 = count.compareAndSet(0, 1); // no
        log.info("result={}", b2);
        boolean b3 = count.compareAndSet(1, 3); // no
        log.info("result={}", b3);
        boolean b4 = count.compareAndSet(2, 4); // 4
        log.info("result={}", b4);
        boolean b5 = count.compareAndSet(3, 5); // no
        log.info("result={}", b5);
        log.info("count:{}", count.get());
    }
}
