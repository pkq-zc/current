package com.buydeem.volatile_ex;

import java.util.concurrent.TimeUnit;

/**
 * Created by zengchao on 2019/11/19.
 */
public class App1 {
    boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        App1 app1 = new App1();
        Thread t1 = new Thread(() -> {
            long i = 0;
            while (app1.flag){
                i++;
            }
            System.out.println("out loop. i= "+i);
        },"t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        app1.flag = false;
        System.out.println("修改flag值为false");
    }

}

