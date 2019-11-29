package com.buydeem.atomic;


import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 引用类型
 * Created by zengchao on 2019/11/28.
 */
public class App3 {
    private static AtomicReference<BigDecimal> i = new AtomicReference<>(BigDecimal.ZERO);
    private static BigDecimal k = BigDecimal.ZERO;
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int j = 0; j < 10000; j++) {
                //进行+1操作
                while (true) {
                    //获取更新前的值
                    BigDecimal old = i.get();
                    //比较old与内存中的值是否一致,compareAndSet该方法第一个参数是预期值,第二个是更新后的值,如果失败,返回false.
                    boolean success = i.compareAndSet(old, old.add(BigDecimal.ONE));
                    if (success) {
                        break;
                    }
                    //不一致重新再试
                }
                k = k.add(BigDecimal.ONE);
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int j = 0; j < 4; j++) {
            pool.execute(runnable);
        }

        pool.shutdown();

        while (!pool.isTerminated()){

        }

        System.out.println("i.get() = " + i.get());
        System.out.println("k = " + k);

    }
}
