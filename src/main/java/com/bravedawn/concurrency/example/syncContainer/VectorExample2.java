package com.bravedawn.concurrency.example.syncContainer;

import java.util.Vector;

/**
 * @Author 冯晓
 * @Date 2020/6/10 19:22
 */
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true){
            for (int i = 0; i < 10; i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++){
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++){
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
