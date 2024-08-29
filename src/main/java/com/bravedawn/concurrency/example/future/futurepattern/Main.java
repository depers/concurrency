package com.bravedawn.concurrency.example.future.futurepattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/9 21:44
 */

@Slf4j
public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        // 系统启动调用client发出请求
        // 这个data是FutureData
        Data data = client.request("name");
        log.info("请求完毕");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        // 这个地方会阻塞
        log.info("数据={}", data.getResult());
    }
}
