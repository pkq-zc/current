package com.buydeem.synchroniz;

/**
 * 可重入性
 * Created by zengchao on 2019/10/30.
 */
public class App5 {
    private final Object o;

    public App5(Object o) {
        this.o = o;
    }

    public void method1(){
        synchronized (o){
            System.out.println(Thread.currentThread().getName()+":执行method1");
            method2();
        }
    }

    public void method2(){
        synchronized (o){
            System.out.println(Thread.currentThread().getName()+":执行method2");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        App5 app = new App5(new Object());

        Thread t1 = new Thread(app::method1);
        Thread t2 = new Thread(app::method1);
        Thread t3 = new Thread(app::method1);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
