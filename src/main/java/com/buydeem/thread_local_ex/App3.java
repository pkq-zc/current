package com.buydeem.thread_local_ex;

/**
 * Created by zengchao on 2019/11/27.
 */
public class App3 {
    public static void main(String[] args) {
        //创建
        ThreadLocal<Integer> local1 = ThreadLocal.withInitial(() -> 0);
        ThreadLocal<Integer> local2 = new ThreadLocal<>();

        //get
        Integer i = local1.get();
        //set
        local1.set(2);
        //remove
        local1.remove();
    }
}
