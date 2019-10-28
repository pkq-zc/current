package com.buydeem.thread;

/**
 * Created by zengchao on 2019/10/28.
 */
public class WaitingDemo2 {
    public static void main(String[] args) throws InterruptedException {
        //  线程1
        Thread t1 = new Thread(() -> {
            //线程2
            Thread t2 = new Thread(() -> {
                while (true){

                }
            });
            t2.setName("线程2");
            t2.start();
            try {
                //线程2调用join
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        t1.setName("线程1");
        t1.start();

        //确保线程1和线程2已开始运行
        Thread.sleep(500L);
        System.out.println(t1.getState());
    }
}
