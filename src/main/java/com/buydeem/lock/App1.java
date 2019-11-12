package com.buydeem.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 * Created by zengchao on 2019/11/4.
 */
public class App1 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(Thread.currentThread().getName()+":执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+":执行完成");
        }, "t2");
        t2.start();

        Thread.sleep(1000L);
        //唤醒t1,t2
        synchronized (lock){
            lock.notify();
        }
        LockSupport.unpark(t2);
    }
}
