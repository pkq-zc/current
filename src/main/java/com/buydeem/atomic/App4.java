package com.buydeem.atomic;

import lombok.AllArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * AtomicReferenceFieldUpdater 原子更新引用类型的字段
 * Created by zengchao on 2019/11/29.
 */
public class App4 {
    //使用给定的字段为User对象创建和返回一个更新器
    private static AtomicReferenceFieldUpdater<User, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(User.class, Integer.class, "score");
    private static User user = new User("tom",0);
    private static AtomicInteger counter = new AtomicInteger(0);
    private static Integer k = 0;
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
                k++;
                //score+1操作
                while (true) {
                    //获取旧值
                    Integer old = updater.get(user);
                    //通过cas算法更新新值
                    boolean success = updater.compareAndSet(user, old, old + 1);
                    if (success) {
                        break;
                    }
                }
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int j = 0; j < 4; j++) {
            pool.execute(runnable);
        }

        pool.shutdown();

        while (!pool.isTerminated()){

        }

        System.out.println("user = " + user.score);
        System.out.println("counter = " + counter.get());
        System.out.println("k = " + k);
    }
}

@AllArgsConstructor
class User {
    private String userName;
    //该字段不能是private
    volatile Integer score;
}
