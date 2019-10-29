package com.buydeem.thread;

/**
 * 线程处于NEW状态
 * Created by zengchao on 2019/10/28.
 */
public class InterruptDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("线程执行完成");
        });
        //调用中断方法
        System.out.println("t1.getState() = " + t1.getState());
        t1.interrupt();
        Thread.sleep(1000);
        System.out.println("t1.isInterrupted() = " + t1.isInterrupted());
        t1.start();
    }
}
