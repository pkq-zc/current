package com.buydeem.volatile_ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zengchao on 2019/11/22.
 */
public class App3 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Runnable runnable = () -> {
            Singleton singleton = Singleton.getInstance();
            System.out.println("singleton = " + singleton);
        };

        for (int i = 0; i < 100; i++) {
            service.execute(runnable);
        }

        service.shutdown();
    }
}

class Singleton {
    private static  Singleton singleton;

    private Singleton(){}

    public static Singleton getInstance(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
