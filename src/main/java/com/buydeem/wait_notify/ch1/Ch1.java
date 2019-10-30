package com.buydeem.wait_notify.ch1;

/**
 * Created by zengchao on 2019/10/29.
 */
public class Ch1 {
    public static void main(String[] args) {
        Person p = new Person(1,0);
        Thread p1 = new Thread(new Producers(p), "生产者线程-1");
        Thread p2 = new Thread(new Producers(p), "生产者线程-2");
        Thread c1 = new Thread(new Consumer(p), "消费者线程-1");
        Thread c2 = new Thread(new Consumer(p), "消费者线程-2");
        p1.start();
        c1.start();
        c2.start();
        while (true){

        }
    }
}

class Person {
    private final Integer maxStore;
    private Integer currentStore;

    public Person(Integer maxStore, Integer currentStore) {
        this.maxStore = maxStore;
        this.currentStore = currentStore;
    }

    /**
     * 增加库存
     */
    public synchronized void add() {
        while (currentStore >= maxStore){
            try {
                System.out.println(Thread.currentThread().getName() + "数量已满,等待消费");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentStore++;
        System.out.println(Thread.currentThread().getName() + ":生产之后的数量为[" + currentStore + "]");
        this.notifyAll();
    }

    /**
     * 减少库存
     */
    public synchronized void remove() {

        while (currentStore <= 0){
            try {
                System.out.println(Thread.currentThread().getName() + "数量不足,等待生产");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentStore--;
        System.out.println(Thread.currentThread().getName() + ":消费之后的数量为[" + currentStore + "]");
        this.notifyAll();
    }
}

/**
 * 生产者
 */
class Producers implements Runnable{
    private final Person p;

    public Producers(Person p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true){
            p.add();
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable{
    private final Person p;

    public Consumer(Person p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true){
            p.remove();
        }
    }
}


