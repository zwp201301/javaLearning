package com.xh.java.threadSequential;

/**
 * T1、T2、T3三个线程顺序执行
 *
 * @author zhuwenping
 */
public class ThreadSeqExeWithJoin1 {

    public static void main(String[] args) throws Exception {
        Thread1 t1 = new Thread1();
        t1.start();
        t1.join();

        Thread2 t2 = new Thread2();
        t2.start();
        t2.join();

        Thread3 t3 = new Thread3();
        t3.start();
        t3.join();
    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("thread1");
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            System.out.println("thread2");
        }
    }

    private static class Thread3 extends Thread {
        @Override
        public void run() {
            System.out.println("thread3");
        }
    }

}
