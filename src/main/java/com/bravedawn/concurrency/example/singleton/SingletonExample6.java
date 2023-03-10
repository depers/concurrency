package com.bravedawn.concurrency.example.singleton;

/**
 * Created by 冯晓 on 2019/3/5.
 */

import com.bravedawn.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类转载时进行创建
 * 饿汉模式如果构造函数内的进行过多的操作，在类加载时就会很慢从而造成性能问题；如果使用饿汉模式只进行类的加载而不使用会造成资源浪费
 */
@ThreadSafe
public class SingletonExample6 {
    // 构造函数
    private SingletonExample6(){

    }

    // 这里需要注意的就是一定要把静态域放在静态代码块前面，先执行静态域在执行静态代码块

    // 单例对象
    // 静态域
    private static SingletonExample6 instance = null;

    // 静态代码块
    static {
        instance = new SingletonExample6();
    }

    // 静态的工厂方法
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
