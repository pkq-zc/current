package com.buydeem.thread;

/**
 * 线程处于 RUNABLE
 * Created by zengchao on 2019/10/28.
 */
public class InterruptDemo3 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true){

            }
        });
        t1.start();
        System.out.println("t1.getState() = " + t1.getState());
        //调用中断方法
        t1.interrupt();
        Thread.sleep(1000);
        System.out.println("t1.isInterrupted() = " + t1.isInterrupted());
    }
}
