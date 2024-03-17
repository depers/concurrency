package com.bravedawn.concurrency.example.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : fengx9
 * @Project : concurrency-master
 * @Date : Created in 2024-03-13 16:49
 */
@Slf4j
public class ThreadLocalExample2 {



    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("dev_fengxiao@163.com");
        threadLocal.get();
    }




}
