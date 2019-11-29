package com.buydeem.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  ABA 示例
 * Created by zengchao on 2019/11/29.
 */
public class App5 {
    private static AtomicInteger i = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            String name = Thread.currentThread().getName();
            //获取值
            int old = App5.i.get();
            System.out.println(name+" ==> i ="+ old);
            //  休眠1秒
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //更新值
            boolean success = i.compareAndSet(old, 100);
            if (success){
                System.out.println(name+" CAS更新操作成功 ==> " +i.get());
            }else {
                System.out.println(name+" CAS更新操作失败 ==> " +i.get());
            }
        }, "t1");

        Thread t2 = new Thread(()->{
            String name = Thread.currentThread().getName();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //修改成2
            i.set(2);
            System.out.println(name+" ==> i = "+i.get());
            //修改成1
            i.set(1);
            System.out.println(name+" ==> i = "+i.get());
        },"t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
