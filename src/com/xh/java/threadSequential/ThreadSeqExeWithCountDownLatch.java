package com.xh.java.threadSequential;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch(int count) //实例化一个倒计数器，count指定计数个数
 * countDown() // 计数减一
 * await() //等待，当计数减到0时，所有线程并行执行
 *
 * @author zhuwenping
 */
public class ThreadSeqExeWithCountDownLatch {
    public static void main(String[] args) {
        //c0计数器为0，第一个执行
        CountDownLatch c0 = new CountDownLatch(0);
        //计数器为1
        CountDownLatch c1 = new CountDownLatch(1);
        //计数器为1
        CountDownLatch c2 = new CountDownLatch(1);

        Thread t1 = new Thread(new Work(c0, c1));
        //c0为0，t1可以执行。t1的计数器减1

        Thread t2 = new Thread(new Work(c1, c2));
        //t1的计数器为0时，t2才能执行。t2的计数器c2减1

        Thread t3 = new Thread(new Work(c2, c2));
        //t2的计数器c2为0时，t3才能执行

        t1.start();
        t2.start();
        t3.start();

    }

    //定义Work线程类，需要传入开始和结束的CountDownLatch参数
    static class Work implements Runnable {
        CountDownLatch c1;
        CountDownLatch c2;

        Work(CountDownLatch c1, CountDownLatch c2) {
            super();
            this.c1 = c1;
            this.c2 = c2;
        }

        @Override
        public void run() {
            try {
                c1.await();//前一线程为0才可以执行
                System.out.println("thread start:" + Thread.currentThread().getName());
                c2.countDown();//本线程计数器减少
            } catch (InterruptedException e) {
            }

        }
    }
}
