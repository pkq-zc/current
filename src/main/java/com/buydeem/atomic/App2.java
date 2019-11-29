package com.buydeem.atomic;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 数组类型
 * Created by zengchao on 2019/11/28.
 */
public class App2 {
    private static int[] array1 = {0,0};
    private static int[] array2 = {0,0};
    //通过数组初始化,并不会影响原数组的数据
    private static AtomicIntegerArray array3 = new AtomicIntegerArray(array2);
    //设置长度初始化
    private static AtomicIntegerArray array4 = new AtomicIntegerArray(2);

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                //获取数组第一个元素+1并重新写入
                array1[0] = array1[0] + 1;
                array3.incrementAndGet(0);
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            pool.execute(runnable);
        }

        pool.shutdown();

        while (!pool.isTerminated()){

        }

        System.out.println("Arrays.toString(array1) = " + Arrays.toString(array1));
        System.out.println("Arrays.toString(array2) = " + Arrays.toString(array2));
        System.out.println("array3 = " + array3);
    }
}
