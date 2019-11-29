package com.buydeem.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基础类型
 * Created by zengchao on 2019/11/28.
 */
public class App1 {
    //设置一个原子计数变量
    private static AtomicInteger counter1 = new AtomicInteger(0);
    //设置一个普通的计数变量
    private static Integer counter2 = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                //进行+1操作
                counter1.incrementAndGet();
                counter2++;
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            pool.execute(runnable);
        }

        pool.shutdown();

        while (!pool.isTerminated()){

        }
        System.out.println("counter1 = " + counter1);
        System.out.println("counter2 = " + counter2);

    }
}
