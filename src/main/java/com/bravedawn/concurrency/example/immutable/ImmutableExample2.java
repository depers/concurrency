package com.bravedawn.concurrency.example.immutable;

import com.bravedawn.concurrency.annoations.NotThreadSafe;
import com.bravedawn.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * Created by 冯晓 on 2019/3/6.
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    // Collections修改的对象不能是final的
    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(2, 3);
        map = Collections.unmodifiableMap(map);
    }


    public static void main(String[] args) {
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

}
