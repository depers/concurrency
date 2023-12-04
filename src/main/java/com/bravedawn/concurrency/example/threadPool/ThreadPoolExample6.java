package com.bravedawn.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample6 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        /**
         * scheduleWithFixedDelay以指定的延时去执行任务
         * scheduleWithFixedDelay，不管任务执行多久，都会等上一次任务执行完毕后再延迟delay后去执行下次任务。
         *
         * 下面的案例：任务启动之后延时1秒开始执行，每隔3秒执行一次任务，一直执行下去
         */
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                log.info("schedule run");
            }
        }, 1, 3, TimeUnit.SECONDS);



    }
}
