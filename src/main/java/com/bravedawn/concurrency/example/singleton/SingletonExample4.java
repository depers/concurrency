package com.bravedawn.concurrency.example.singleton;

/**
 * Created by 冯晓 on 2019/3/5.
 */

import com.bravedawn.concurrency.annoations.NotRecommend;
import com.bravedawn.concurrency.annoations.NotThreadSafe;
import com.bravedawn.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式 --> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample4 {
    // 构造函数
    private SingletonExample4(){

    }

    // 单例对象
    private static SingletonExample4 instance = null;

    // 静态的工厂方法
    public static SingletonExample4 getInstance(){
        if (instance == null){ // 双重检测机制
            synchronized (SingletonExample4.class){ // 同步锁
                if (instance == null){
                    instance = new SingletonExample4();
                }
            }

        }
        return instance;
    }


    // 执行instance = new SingletonExample4();
    // 1.memory = allocate() 分配对象的内存空间
    // 2.ctorInstance() 初始化对象
    // 3.instance = memory 设置instance指向刚分配的内存空间

    // 由于JVM和cpu优化，发生了指令重排，导致上述三步执行发生变化：
    // 1.memory = allocate() 分配对象的内存空间
    // 2.instance = memory 设置instance指向刚分配的
    // 3.ctorInstance() 初始化对象

    // 线程不安全的情况
    // 假设现在有两个线程A，B
    // 线程A在执行instance = new SingletonExample4();时，发生指令重排，执行到2.instance = memory 设置instance指向刚分配的
    // 线程B此时执行到第一个if (instance == null){时，认为instance已经有值了，所以直接执行了return instance;
    // 但是A进程还没有执行3.ctorInstance() 初始化对象。
    // 线程B一旦拿到还没有初始化的instance对象就会出错，所以不是线程安全的



}
