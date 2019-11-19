package com.buydeem.join;

/**
 * Created by zengchao on 2019/11/19.
 */
public class App1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("加载资源A....");
        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println("加载资源B");
        }, "t2");

        t1.start();
        t2.start();

        System.out.println("等待任务t1完成");
        t1.join();
        System.out.println("等待任务t2完成");
        t2.join();

        System.out.println("所有资源加载完毕");

    }
}
