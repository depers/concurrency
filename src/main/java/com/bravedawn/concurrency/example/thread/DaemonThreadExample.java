package com.bravedawn.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-11-30 10:34
 */


@Slf4j
public class DaemonThreadExample extends Thread{

    /**
     * 守护线程，是指为其他线程服务的线程。
     * 守护线程不能持有需要关闭的资源（如打开文件等）
     */


    @Override
    public void run() {
        while (true) {
            log.info("time={}", LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        DaemonThreadExample daemonThread = new DaemonThreadExample();
        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}
