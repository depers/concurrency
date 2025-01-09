package com.bravedawn.concurrency.example.threadPool.executor;

import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorExample2 {


    /**
     * 测试ThreadPoolExecutor中ctl变量的作用
     */

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int COUNT_MASK = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~COUNT_MASK; } // 计算线程池的状态
    private static int workerCountOf(int c)  { return c & COUNT_MASK; } // 计算返回当前线程数
    // 按位或，真真为真，真假为真，假假为假
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    private static final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));


    public static void main(String[] args) {
        System.out.println("RUNNING:接受新任务并处理任务队列的任务, i = " + RUNNING + "，二进制数是" + Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN:不接受新任务，但处理任务队列的任务, i = " + SHUTDOWN + "，二进制数是" + Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP:不接受新任务，不处理任务队列的任务，并中断正在进行的任务, i = " + STOP + "，二进制数是" + Integer.toBinaryString(STOP));
        System.out.println("TIDYING:所有任务都已终止，workerCount 为 0，线程转换为 TIDYING 状态，将运行 terminated() 钩子方法, i = " + TIDYING + "，二进制数是" + Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED:terminate() 方法执行完成, i = " + TERMINATED + "，二进制数是" + Integer.toBinaryString(TERMINATED));

        System.out.println("ctl的初始状态：" + ctl.get() + "，二进制数是" + Integer.toBinaryString(ctl.get()));

        System.out.println(Integer.toBinaryString(COUNT_MASK)); // 11111111111111111111111111111
//        System.out.println(Integer.toBinaryString(~COUNT_MASK)); // 11111111111111111111111111111

    }
}
