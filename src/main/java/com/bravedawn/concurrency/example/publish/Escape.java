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

    private int thisCanBeEscape;


    public Escape(){
        new InnerClass();
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
