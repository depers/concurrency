package com.bravedawn.concurrency.example.singleton;

/**
 * Created by 冯晓 on 2019/3/5.
 */

import com.bravedawn.concurrency.annoations.NotRecommend;
import com.bravedawn.concurrency.annoations.NotThreadSafe;
import com.bravedawn.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * 饿汉模式如果构造函数内的进行过多的操作，在类加载时就会很慢从而造成性能问题；如果使用饿汉模式只进行类的加载而不使用会造成资源浪费
 */
@ThreadSafe
@NotRecommend
public class SingletonExample2 {
    // 构造函数
    private SingletonExample2(){

    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态的工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }

}
