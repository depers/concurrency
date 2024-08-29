package com.bravedawn.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/18 11:48
 *
 * getAndSet方法
 */
public class AtomicBooleanExample {

    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        boolean andSet = atomicBoolean.getAndSet(true);
        System.out.println("改之前的值：" + andSet);
        System.out.println("改之后的值：" + atomicBoolean.get());

    }
}
