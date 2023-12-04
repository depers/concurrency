package com.bravedawn.concurrency.example.publish;

import com.bravedawn.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created by 冯晓 on 2019/3/5.
 */
@Slf4j
@NotThreadSafe
public class UnSafePublish {

    /**
     * 发布对象：是一个对象能够被当前范围之外的代码所使用
     */

    private String[] state = {"a", "b", "c"};

    public String[] getState(){
        return state;
    }

    public static void main(String[] args) {
        UnSafePublish unSafePublish = new UnSafePublish();
        log.info(Arrays.toString(unSafePublish.getState()));

        // 当前对象的私有属性可以被其它线程任意修改
        unSafePublish.getState()[0] = "d";
        log.info(Arrays.toString(unSafePublish.getState()));
    }
}
