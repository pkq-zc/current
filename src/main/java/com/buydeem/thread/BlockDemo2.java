package com.buydeem.thread;

/**
 * Created by zengchao on 2019/10/28.
 */
public class BlockDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();

        Thread t1 = new Thread(() -> account.get(100));
        t1.setName("取钱线程");
        t1.start();

        Thread.sleep(500L);

        Thread t2 = new Thread(() -> account.add(100));
        t2.setName("存钱线程");
        t2.start();

        Thread.sleep(500L);
        //
        System.out.println(t1.getName()+":"+t1.getState());


    }
}

class Account{
    private int amount;

    /**
     * 存入金额
     * @param cash
     */
    public synchronized void add(int cash){
        System.out.println(Thread.currentThread().getName()+":准备存钱");
        amount = amount + cash;
        notify();
        try {
            //模拟化时间再做一些事
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取钱
     * @param cash
     */
    public synchronized void get(int cash){
        System.out.println(Thread.currentThread().getName()+":准备取钱");
        while (amount <= cash){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        amount = amount - cash;
    }
}
