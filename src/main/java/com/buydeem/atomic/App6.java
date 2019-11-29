package com.buydeem.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * ABA解决方案 -- AtomicMarkableReference
 * Created by zengchao on 2019/11/29.
 */
public class App6 {
    private static AtomicMarkableReference<Integer> i = new AtomicMarkableReference<>(1,false);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            String name = Thread.currentThread().getName();
            boolean[] update = new boolean[1];
            //获取值
            int old = i.get(update);
            System.out.println(name+" ==> i = "+ old + " update = "+update[0]);
            //  休眠1秒
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //更新值
            boolean success = i.compareAndSet(old, 100,update[0],!update[0]);
            if (success){
                System.out.println(name+" CAS更新操作成功 ==> " +i.get(update) + " update = "+update[0]);
            }else {
                System.out.println(name+" CAS更新操作失败 ==> " +i.get(update) + " update = "+update[0]);
            }
        }, "t1");

        Thread t2 = new Thread(()->{
            String name = Thread.currentThread().getName();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean[] update = new boolean[1];
            //修改成2
            i.set(2,true);
            System.out.println(name+" ==> i = "+i.get(update) + " update = "+update[0]);
            //修改成1
            i.set(1,true);
            System.out.println(name+" ==> i = "+i.get(update) + " update = "+update[0]);
        },"t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
