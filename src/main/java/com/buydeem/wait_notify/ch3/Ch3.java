package com.buydeem.wait_notify.ch3;

/**
 * Created by zengchao on 2019/11/19.
 */
public class Ch3 {
    public static void main(String[] args) {
        Object resourceA = new Object();
        Object resourceB = new Object();

        Runnable runnable = () -> {
            synchronized (resourceA){
                System.out.println(Thread.currentThread().getName()+":获取resourceA的锁");
                synchronized (resourceB){
                    System.out.println(Thread.currentThread().getName()+":获取resourceB的锁");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":wait唤醒之后执行内容");
                }
            }
        };

        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        t1.start();
        t2.start();
    }
}
