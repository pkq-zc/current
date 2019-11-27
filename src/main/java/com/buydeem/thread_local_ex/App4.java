package com.buydeem.thread_local_ex;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zengchao on 2019/11/27.
 */
public class App4 {
    private static ThreadLocal<List<Integer>> local = ThreadLocal.withInitial(LinkedList::new);
    public static void main(String[] args) {
        Runnable runnable = () -> {
            int i = new Random().nextInt(1000);
            System.out.println(System.identityHashCode(local.get()));
            local.get().add(i);
            //System.out.println(Thread.currentThread().getName()+" : "+local.get());
            System.out.println(local.get());
            local.remove();
        };

        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            pool.execute(runnable);
        }

        pool.shutdown();

    }
}
