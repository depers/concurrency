package com.bravedawn.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/24 21:08
 *
 * 如果初始化的许可是0
 */
@Slf4j
public class SemaphoreExample5 {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(0);


        Thread t = new Thread(() -> {
            log.info("等待获取许可");

            while (true) {
                // 这个逻辑需要写到while循环里
                if(semaphore.tryAcquire()) {
                    log.info("成功获取许可");
                    break;
                } else {
                    log.warn("获取许可失败");
                }
            }

        });

        t.start();

        Thread.sleep(2000);
        log.info("释放许可，唤醒信号量");
        semaphore.release();
        t.join();
    }
}
