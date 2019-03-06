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
        map.put(1, 3);
    }

}
