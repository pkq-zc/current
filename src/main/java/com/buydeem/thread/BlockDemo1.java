package com.buydeem.thread;

/**
 * Created by zengchao on 2019/10/28.
 */
public class BlockDemo1 {
    public static void main(String[] args) throws InterruptedException {
       Counter counter = new Counter();
        Thread t1 = new Thread(() -> counter.increase());
        t1.setName("线程1");
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(() -> counter.increase());
        t2.setName("线程2");
        t2.start();

        Thread.sleep(1000);
        System.out.println("t2.getState() = " + t2.getState());
    }
}

class Counter {
    int number = 0;


    public synchronized void increase(){
        number++;
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}