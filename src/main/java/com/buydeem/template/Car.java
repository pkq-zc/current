package com.buydeem.template;

/**
 * 车
 * Created by zengchao on 2019/11/29.
 */
public abstract class Car {

    /**
     * 进入车里面
     */
    public abstract void into();

    /**
     * 提供动力
     */
    public abstract void providePower();


    //行驶
    public void run(){
        //进入车里面
        into();
        //提供动力
        providePower();
        //车辆启动
        System.out.println("running ....");
    }
}
