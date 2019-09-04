package com.xh.java.threadSequential;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadSeqExeWithBlockingQueue {

    public static void main(String[] args) {
        //blockingQueue保证顺序
        BlockingQueue<Thread> blockingQueue = new LinkedBlockingQueue<Thread>();
        Thread t1 = new Thread(new Work());
        Thread t2 = new Thread(new Work());
        Thread t3 = new Thread(new Work());

        blockingQueue.add(t1);
        blockingQueue.add(t2);
        blockingQueue.add(t3);

        for (int i = 0; i < 3; i++) {
            Thread t = null;
            try {
                t = blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.start();
            //检测线程是否还活着
            while (t.isAlive()) ;
        }
    }

    static class Work implements Runnable {

        @Override
        public void run() {
            System.out.println("thread start:" + Thread.currentThread().getName());
        }
    }
}
