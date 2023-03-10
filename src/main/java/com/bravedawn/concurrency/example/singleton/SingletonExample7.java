package com.bravedawn.concurrency.example.singleton;

import com.bravedawn.concurrency.annoations.Recommend;
import com.bravedawn.concurrency.annoations.ThreadSafe;

/**
 * Created by 冯晓 on 2019/3/5.
 */

/**
 * 枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7(){

    }


    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton(){
            return singleton;
        }
    }
}

