package com.bravedawn.concurrency.example.threadPool.executor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * @Author : fengx9
 * @Project : concurrency
 * @Date : Created in 2023-12-01 10:07
 */

@Slf4j
public class ExecutorExample {


    /**
     * 自定义一个同步线程执行器
     */


    private static class SynchronousExecutor implements Executor {
        /** {@inheritDoc} */
        @Override
        @SuppressWarnings("NullableProblems")
        public void execute(Runnable command)
        {
            try {
                command.run();
            }
            catch (Exception t) {
                log.debug("Failed to execute: {}", command, t);
            }
        }
    }


    public static void main(String[] args) {
        SynchronousExecutor synchronousExecutor = new SynchronousExecutor();
        synchronousExecutor.execute(() -> {
            log.info("这是一个同步执行器");
        });
    }
}
