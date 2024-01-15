package com.bravedawn.concurrency.example.singleton;

/**
 * Created by 冯晓 on 2019/3/5.
 */

import com.bravedawn.concurrency.annoations.NotRecommend;
import com.bravedawn.concurrency.annoations.NotThreadSafe;
import com.bravedawn.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    // 构造函数
    private SingletonExample3(){

    }

    // 单例对象
    private static SingletonExample3 instance = null;

    // 静态的工厂方法
    // 加了synchronized关键字后，虽然保证了线程安全性，但是却带了性能上面的开销
    public static synchronized SingletonExample3 getInstance(){
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }

}
