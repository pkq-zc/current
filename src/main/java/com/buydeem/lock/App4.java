package com.buydeem.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by zengchao on 2019/11/12.
 */
public class App4 {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println("第一次调用unpark");
        LockSupport.unpark(thread);
        System.out.println("第二次调用unpark");
        LockSupport.unpark(thread);
        System.out.println("第一次调用park");
        LockSupport.park();
        System.out.println("第二次调用park");
        LockSupport.park();
        System.out.println("执行完成");
    }
}
