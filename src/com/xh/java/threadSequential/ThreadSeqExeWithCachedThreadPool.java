package com.xh.java.threadSequential;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadSeqExeWithCachedThreadPool {

    public static void main(String[] args) {
        FutureTask<Integer> future1 = new FutureTask<Integer>(new Work(null));
        Thread t1 = new Thread(future1);

        FutureTask<Integer> future2 = new FutureTask<Integer>(new Work(future1));
        Thread t2 = new Thread(future2);

        FutureTask<Integer> future3 = new FutureTask<Integer>(new Work(future2));
        Thread t3 = new Thread(future3);

        t1.start();
        t2.start();
        t3.start();
    }

    static class Work implements Callable<Integer> {
        private FutureTask<Integer> beforeFutureTask;

        public Work(FutureTask<Integer> beforeFutureTask) {
            this.beforeFutureTask = beforeFutureTask;
        }

        @Override
        public Integer call() throws Exception {
            if (beforeFutureTask != null) {
                //阻塞等待
                Integer result = beforeFutureTask.get();
                System.out.println("thread start:" + Thread.currentThread().getName());
            } else {
                System.out.println("thread start:" + Thread.currentThread().getName());
            }
            return 0;
        }
    }
}
