package com.buydeem.happens_before;

/**
 * Created by zengchao on 2019/11/22.
 */
public class App1 {
    public static void main(String[] args) {

    }
}

class A {
    private Integer number = 0;
    private final Object lockA = new Object();
    private final Object lockB = new Object();

    public void method1(){
        synchronized (lockA){
            number = number+1;
        }
    }

    public void method2(){
        synchronized (lockA){
            number = number+2;
        }
    }

    public void method3(){
        synchronized (lockB){
            number = number+2;
        }
    }
}