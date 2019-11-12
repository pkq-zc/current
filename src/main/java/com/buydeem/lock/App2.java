package com.buydeem.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by zengchao on 2019/11/12.
 */
public class App2 {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+":开始执行");
                lock.notify();
            }
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(Thread.currentThread().getName()+":执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+":开始执行");
            LockSupport.unpark(Thread.currentThread());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+":执行完成");

        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
