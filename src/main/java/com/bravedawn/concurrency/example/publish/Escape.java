package com.bravedawn.concurrency.example.publish;

import com.bravedawn.concurrency.annoations.NotRecommend;
import com.bravedawn.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by 冯晓 on 2019/3/5.
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    /**
     * 对象逸出：一种错误的发布。当一个对象还没有构造完成时，就使它被其它线程所见。
     */

    private int thisCanBeEscape;


    public Escape(){
        new InnerClass();
        // 该变量还未完成初始化，this对象就已经逸出
        thisCanBeEscape = 1;
    }

    private class InnerClass{

        public InnerClass(){
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
