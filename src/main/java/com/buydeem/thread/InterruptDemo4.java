package com.buydeem.thread;

/**
 * 线程处于BLOCKED
 * Created by zengchao on 2019/10/28.
 */
public class InterruptDemo4 {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        //t1.始终获得锁
        Thread t1 = new Thread(() -> {
            synchronized (object){
                while (true){

                }
            }
        });
        t1.start();

        Thread.sleep(100L);

        //t2 始终处于BLOCKED
        Thread t2 = new Thread(() -> {
            synchronized (object) {
                System.out.println("线程2执行中");
            }
        });
        t2.start();

        Thread.sleep(100L);

        System.out.println("t2.getState() = " + t2.getState());
        System.out.println("t2.isInterrupted() = " + t2.isInterrupted());
        t2.interrupt();
        System.out.println("t2.isInterrupted() = " + t2.isInterrupted());
    }
}
