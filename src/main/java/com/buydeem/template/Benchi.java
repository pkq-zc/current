package com.buydeem.template;

/**
 * Created by zengchao on 2019/11/29.
 */
public class Benchi extends Car{
    @Override
    public void into() {
        System.out.println("解锁打开车门坐进车里面,插入钥匙点火");
    }

    @Override
    public void providePower() {
        System.out.println("踩油门");
    }
}
