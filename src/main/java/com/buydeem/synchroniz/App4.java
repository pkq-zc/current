package com.buydeem.synchroniz;

/**
 * 同步代码块
 * Created by zengchao on 2019/10/30.
 */
public class App4 implements Runnable{
    static int i = 0;
    private final Object o;

    public App4(Object o) {
        this.o = o;
    }

    public void add(){
        System.out.println("不需要同步的费时代码块1");
        synchronized (o){
            i++;
        }
        System.out.println("不需要同步的费时代码块2");
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        App4 app1 = new App4(lock);
        App4 app2 = new App4(lock);

        Thread t1 = new Thread(app1);
        Thread t2 = new Thread(app2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("i = " + i);

    }
}