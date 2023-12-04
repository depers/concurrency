package com.bravedawn.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample5 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        /**
         * scheduleAtFixedRate作用是以指定的速率去执行任务
         * scheduleAtFixedRate ，是以上一个任务开始的时间计时，period时间过去后，检测上一个任务是否执行完毕，如果上一个任务执行完毕，则当前任务立即执行；
         * 果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行。是以period为间隔来执行任务的，如果任务执行时间小于period，则上次任务执行完成后会间隔period后再去执行下一次任务；
         * 但如果任务执行时间大于period，则上次任务执行完毕后会不间隔的立即开始下次任务。
         *
         * 下面的案例：任务启动之后延时1秒开始执行，每隔3秒执行一次任务，一直执行下去
         */
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("schedule run");
            }
        }, 1, 3, TimeUnit.SECONDS);
        //executorService.shutdown(); 这里不适合调用shutdown，应在适当的时候调用

        /**
         * Timer与Executors.newScheduledThreadPool。只不过他的始终只用一个线程去执行
         * 下面演示的效果是从现在开始执行，每五秒执行一次
         */
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        }, new Date(), 5 * 1000);



    }
}
