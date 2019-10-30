package com.buydeem.synchroniz;

/**
 * Created by zengchao on 2019/10/30.
 */
public class App1 implements Runnable{
    static Integer i = 0;

    public synchronized void add(){
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        App1 app1 = new App1();
        Thread t1 = new Thread(app1);
        Thread t2 = new Thread(app1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i = " + i);
    }
}
