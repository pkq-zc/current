package com.buydeem.synchroniz;

/**
 * 作用在实例方法错误示例
 * Created by zengchao on 2019/10/30.
 */
public class App2 implements Runnable{
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
        App2 app1 = new App2();
        App2 app2 = new App2();
        Thread t1 = new Thread(app1);
        Thread t2 = new Thread(app2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i = " + i);
    }
}
