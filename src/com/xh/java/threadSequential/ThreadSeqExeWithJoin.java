package com.xh.java.threadSequential;

/**
 * T1、T2、T3三个线程顺序执行
 *
 * @author zhuwenping
 */
public class ThreadSeqExeWithJoin {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Work(null));
        Thread t2 = new Thread(new Work(t1));
        Thread t3 = new Thread(new Work(t2));
        t1.start();
        t2.start();
        t3.start();

    }

    static class Work implements Runnable {

        private Thread beforeThread;

        public Work(Thread beforeThread) {
            this.beforeThread = beforeThread;
        }

        @Override
        public void run() {
            if (beforeThread != null) {
                try {
                    beforeThread.join();
                    System.out.println("thread start:" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("thread start:" + Thread.currentThread().getName());
            }
        }
    }
}
