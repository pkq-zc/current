package com.buydeem.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zengchao on 2019/10/24.
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1000);
        Producer p1 = new Producer(queue, "p1");
        Producer p2 = new Producer(queue, "p2");

        Customer c1 = new Customer(queue,"c1");
        Customer c2 = new Customer(queue,"c2");
        Customer c3 = new Customer(queue,"c3");

        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(p1);
        service.execute(p2);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
        service.shutdown();
    }
}


/**
 * 生产者
 */
class Producer implements Runnable{
    private BlockingQueue<String> queue;
    private String name;
    private Random random = new Random();

    public Producer(BlockingQueue<String> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            try {
                int nextInt = random.nextInt(100);
                Thread.sleep(nextInt * 20);
                String value = name + "-"+String.valueOf(nextInt);
                System.out.println(Thread.currentThread().getName()+"-"+name + " 生产: " + value);
                queue.put(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者
 */
class Customer implements Runnable{
    private BlockingQueue<String> queue;
    private String name;

    public Customer(BlockingQueue<String> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            try {
                String value = queue.take();
                System.out.println(Thread.currentThread().getName()+"-"+name + " 消费: " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
