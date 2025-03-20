package com.bravedawn.concurrency.example.parallelstream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @Description : TODO
 * @Author : depers
 * @Project : concurrency
 * @Date : Created in 2025-03-19 17:33
 */
public class ParallelStreamExample2 {


    /**
     * Java并行流的使用
     */

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("  >>> 电脑 CPU 并行处理线程数 :: %s, 并行流公共线程池内线程数 :: %s",
                Runtime.getRuntime().availableProcessors(),
                ForkJoinPool.commonPool().getParallelism()));
        List<String> stringList = Lists.newArrayList();
        List<String> stringList2 = Lists.newArrayList();
        for (int i = 0; i < 9; i++) stringList.add(String.valueOf(i));
        for (int i = 0; i < 3; i++) stringList2.add(String.valueOf(i));

        new Thread(() -> {
            stringList.parallelStream().forEach(each -> {
                System.out.println(Thread.currentThread().getName() + " 开始执行 :: " + each);
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }, "子线程-1").start();

        Thread.sleep(1500);

        new Thread(() -> {
            stringList2.parallelStream().forEach(each -> {
                System.out.println(Thread.currentThread().getName() + " :: " + each);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }, "子线程-2").start();
    }
}
