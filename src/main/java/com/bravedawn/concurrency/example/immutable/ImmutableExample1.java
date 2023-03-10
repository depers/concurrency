package com.bravedawn.concurrency.example.immutable;

import com.bravedawn.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by 冯晓 on 2019/3/6.
 */

@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(2, 3);
    }


    public static void main(String[] args) {
        // 基础数据类型使用final修饰不能再修改他的值了
        /*
        a = 2;
        b = "2";
        */

        // final 使引用不变，也就不能引用其它对象
        // map = Maps.newHashMap();

        // 但是被引用的对象本身是可以修改的
        map.put(1, 3);
        System.out.println(map.get(1));
    }

    private void test(final int c){
        // 使用final修饰的参数也不能被修改
        //c = 1;
    }

}
