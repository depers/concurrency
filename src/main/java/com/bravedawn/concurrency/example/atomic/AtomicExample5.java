package com.bravedawn.concurrency.example.atomic;

import com.bravedawn.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    /**
     * 这个字段必须定义为volatile
     */
    @Getter
    public volatile int count = 100;


    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100, 120)){
           log.info("update success 1, {}", example5.getCount());
        }
        if (updater.compareAndSet(example5, 100, 120)){
            log.info("update success 2, {}", example5.getCount());
        } else{
            log.info("update failed, {}", example5.getCount());
        }
    }
}
