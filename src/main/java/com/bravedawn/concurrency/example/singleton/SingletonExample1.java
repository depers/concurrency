package com.bravedawn.concurrency.example.singleton;

/**
 * Created by 冯晓 on 2019/3/5.
 */

import com.bravedawn.concurrency.annoations.NotRecommend;
import com.bravedawn.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample1 {
    // 构造函数
    private SingletonExample1(){

    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态的工厂方法
    public static SingletonExample1 getInstance(){
        if (instance == null){
            // 这里存在线程不安全，若有两个线程通知执行到这一步
            instance = new SingletonExample1();
        }
        return instance;
    }

}
