package com.bravedawn.concurrency.example.future.futurepattern;

import java.util.concurrent.Future;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/9 21:41
 */
public class Client {

    public Data request(String queryStr) {

        // 返回Data对象，也就立刻返回futureData，并新开启线程装配realData
        // FutureData的装配是很快的，这只是一个虚拟的Data
        FutureData future = new FutureData();
        new Thread(() -> {
            RealData realData = new RealData(queryStr);
            future.setRealData(realData);
        }).start();

        return future;
    }
}
