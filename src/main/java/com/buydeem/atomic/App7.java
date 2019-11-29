package com.buydeem.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA 解决方案2  AtomicStampedReference
 * Created by zengchao on 2019/11/29.
 */
public class App7 {
    private static AtomicStampedReference<Integer> i = new AtomicStampedReference<>(1,0);
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            String name = Thread.currentThread().getName();
            int[] update = new int[1];
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
            boolean success = i.compareAndSet(old, 100,update[0],update[0]+1);
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
            int[] update = new int[1];
            //修改成2
            i.set(2,update[0]+1);
            System.out.println(name+" ==> i = "+i.get(update) + " update = "+update[0]);
            //修改成1
            i.set(1,update[0]+1);
            System.out.println(name+" ==> i = "+i.get(update) + " update = "+update[0]);
        },"t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
