package com.buydeem.thread;

/**
 * Created by zengchao on 2019/10/28.
 */
public class WaitingDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o){
                System.out.println("线程1开始执行wait()");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1执行完成");
            }
        });
        t1.start();
        //确保线程1先执行
        Thread.sleep(100);

        Thread t2 = new Thread(() -> {
            synchronized (o){
                System.out.println("线程2开始执行");
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2调用notify()");
                o.notify();
            }
        });
        t2.start();

        System.out.println("线程1状态:"+t1.getState());

    }
}
