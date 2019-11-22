package com.buydeem.volatile_ex;

/**
 * Created by zengchao on 2019/11/22.
 */
public class App2 {
    private static volatile int sum = 0;
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                sum++;
            }
        };

        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        Thread t3 = new Thread(runnable, "t3");

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println("sum = " + sum);
    }
}
