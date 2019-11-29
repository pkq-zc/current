package com.buydeem.template;

/**
 * Created by zengchao on 2019/11/29.
 */
public class Ofo extends Car{
    @Override
    public void into() {
        System.out.println("扫码开锁坐在座椅上");
    }

    @Override
    public void providePower() {
        System.out.println("用脚踩踏板");
    }
}
