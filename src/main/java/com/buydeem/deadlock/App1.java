package com.buydeem.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可能导致死锁
 * Created by zengchao on 2019/11/19.
 */
public class App1 {
    public static void main(String[] args) throws InterruptedException {
        Lock lockA = new ReentrantLock();
        Lock lockB = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                lockA.lock();
                System.out.println(Thread.currentThread().getName()+":获取到lockA");
                try {
                    System.out.println(Thread.currentThread().getName()+":获取到lockB");
                    lockB.lock();
                }finally {
                    System.out.println(Thread.currentThread().getName()+":释放到lockB");
                    lockB.unlock();
                }
            }finally {
                System.out.println(Thread.currentThread().getName()+":释放到lockA");
                lockA.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                lockB.lock();
                System.out.println(Thread.currentThread().getName()+":获取到lockB");
                try {
                    System.out.println(Thread.currentThread().getName()+":获取到lockA");
                    lockA.lock();
                }finally {
                    System.out.println(Thread.currentThread().getName()+":释放到lockA");
                    lockA.unlock();
                }
            }finally {
                System.out.println(Thread.currentThread().getName()+":释放到lockB");
                lockB.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("执行完成");

    }
}
