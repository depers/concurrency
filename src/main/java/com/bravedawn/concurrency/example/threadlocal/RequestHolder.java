package com.bravedawn.concurrency.example.threadlocal;

/**
 * Created by 冯晓 on 2019/3/6.
 */

public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    // 该方法应该在请求已经到了后端服务器，在进行处理之前调用
    // id会存储在threadLocal的一个map中，key是当前线程的id，值则是这里的id
    public static void add(Long id){
        requestHolder.set(id);
    }


    public static Long getId(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }

}
