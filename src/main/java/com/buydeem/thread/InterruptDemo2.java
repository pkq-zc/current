package com.buydeem.thread;

/**
 * 线程处于TERMINATED
 * Created by zengchao on 2019/10/28.
 */
public class InterruptDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("线程执行完成");
        });
        t1.start();
        t1.join();
        System.out.println("t1.getState() = " + t1.getState());
        //调用中断方法
        t1.interrupt();
        System.out.println("t1.isInterrupted() = " + t1.isInterrupted());
    }
}
