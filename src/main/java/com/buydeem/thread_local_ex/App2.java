package com.buydeem.thread_local_ex;

/**
 * Created by zengchao on 2019/11/27.
 */
public class App2 {
    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            String setName = Thread.currentThread().getName();
            local.set(setName);
            String getName = local.get();
            System.out.println(Thread.currentThread().getName() + " : getName = " + getName);
        };

        new Thread(runnable,"t1").start();
        new Thread(runnable,"t2").start();
        new Thread(runnable,"t3").start();
        new Thread(runnable,"t4").start();

        Thread.sleep(1000L);
    }
}
