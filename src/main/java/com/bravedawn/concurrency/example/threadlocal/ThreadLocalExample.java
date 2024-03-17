package com.bravedawn.concurrency.example.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-03-13 16:49
 */
@Slf4j
public class ThreadLocalExample {

    public static void main(String[] args) throws InterruptedException {
        Thread main = Thread.currentThread();

        A a = getValue();
        //getValue();

        log.info("执行gc");
        System.gc();
        Thread.sleep(2000);

        System.out.println(main);
    }


    private static A getValue() {
        A a = new A();
        System.out.println("value=" + a.get());
        return a;
    }


    public static class A {
        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "字符串");

        public String get() {
            return threadLocal.get();
        }

        public void set(String str) {
            threadLocal.set(str);
        }
    }

}
