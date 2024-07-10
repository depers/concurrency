package com.bravedawn.concurrency.example.future.futurepattern;

/**
 * @author : depers
 * @program : concurrency
 * @date : Created in 2024/7/9 21:35
 */
public class RealData implements Data{

    protected String result;


    public RealData(String param) {
        // 真实数据的构造会很慢，需要用户等待很久，这里使用sleep进行模拟
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        result = buffer.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
