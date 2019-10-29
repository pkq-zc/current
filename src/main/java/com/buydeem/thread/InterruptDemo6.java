package com.buydeem.thread;

/**
 * Created by zengchao on 2019/10/28.
 */
public class InterruptDemo6 {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (object){
                try {
                    object.wait(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("中断异常退出");
                    System.out.println("Thread.interrupted() = " + Thread.interrupted());
                }
            }
        });
        t1.start();

        Thread.sleep(100L);
        System.out.println("t1.isInterrupted() = " + t1.isInterrupted());
        System.out.println("t1.getState() = " + t1.getState());
        t1.interrupt();
    }
}
