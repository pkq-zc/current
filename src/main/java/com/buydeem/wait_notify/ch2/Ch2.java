package com.buydeem.wait_notify.ch2;

/**
 * Created by zengchao on 2019/10/30.
 */
public class Ch2 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Runnable runnable = () -> {
            synchronized (o){
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true){

                }
            }
        };

        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        Thread t3 = new Thread(runnable, "t3");
        t1.start();
        t2.start();
        t3.start();
        //确保t1.t2.t3正常启动
        Thread.sleep(1000L);

        System.out.println("t1.getState() = " + t1.getState());
        System.out.println("t2.getState() = " + t2.getState());
        System.out.println("t3.getState() = " + t3.getState());

        //调用notifyAll唤醒所有
        Thread t4 = new Thread(() -> {
            synchronized (o) {
                o.notifyAll();
                System.out.println("============调用了notifyAll,代码块还未执行完===============");
                System.out.println("t1.getState() = " + t1.getState());
                System.out.println("t2.getState() = " + t2.getState());
                System.out.println("t3.getState() = " + t3.getState());

            }
        });
        t4.start();
        //确保 调用notifyAll的线程执行完
        Thread.sleep(1000L);
        System.out.println("============调用了notifyAll,代码块已经执行完===============");
        System.out.println("t1.getState() = " + t1.getState());
        System.out.println("t2.getState() = " + t2.getState());
        System.out.println("t3.getState() = " + t3.getState());
    }
}
