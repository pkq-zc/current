package com.buydeem.synchroniz;

/**
 * Created by zengchao on 2019/10/30.
 */
public class App3 implements Runnable{
    static Integer i = 0;

    public static synchronized void add(){
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        App3 app1 = new App3();
        App3 app2 = new App3();
        Thread t1 = new Thread(app1);
        Thread t2 = new Thread(app2);
        Thread t3 = new Thread(() -> {
            for (int j = 0; j < 100000; j++) {
                synchronized (App3.class){
                    i++;
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("i = " + i);
    }
}
