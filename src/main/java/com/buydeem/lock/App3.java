package com.buydeem.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by zengchao on 2019/11/12.
 */
public class App3 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread thread = Thread.currentThread();
                    System.out.println(thread.getName()+":响应中断退出");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            LockSupport.park();
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName()+":响应中断退出");
        }, "t2");

        t1.start();
        t2.start();
        Thread.sleep(1000L);
        System.out.println("t1.getState() = " + t1.getState());
        System.out.println("t2.getState() = " + t2.getState());
        t1.interrupt();
        t2.interrupt();
    }
}
