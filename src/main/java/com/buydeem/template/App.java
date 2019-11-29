package com.buydeem.template;

/**
 * Created by zengchao on 2019/11/29.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("========ofo========");
        Car car1 = new Ofo();
        car1.run();
        System.out.println("========Benchi========");
        Car car2 = new Benchi();
        car2.run();
    }
}
