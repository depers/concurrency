package com.bravedawn.concurrency.example.future.futurepattern;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/9 21:34
 */

public class FutureData implements Data{

    protected RealData realData;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }

        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }
}
