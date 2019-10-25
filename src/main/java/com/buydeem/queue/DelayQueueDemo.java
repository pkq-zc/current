package com.buydeem.queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 使用延迟队列实现简单的定时任务示例
 * Created by zengchao on 2019/10/25.
 */
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建延时队列
        DelayQueue<DelayTask> queue = new DelayQueue<>();
        int count = 10;
        Random random = new Random();
        //循环添加任务
        for (int i = 0; i < count; i++) {
            int second = random.nextInt(10) + 1;
            DelayTask task = new DelayTask(second * 1000L, "任务[" + i + "]");
            queue.put(task);
        }

        //获取任务线程
        Thread work = new Thread(() -> {
            while (true){
                try {
                    DelayTask take = queue.take();
                    System.out.println(Thread.currentThread().getName() + " take = " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        work.setName("获取延时任务线程");
        work.start();

        while (true){

        }

    }
}


/**
 * 延时任务
 */
class DelayTask implements Delayed {
    /**
     * 延迟时间
     */
    private final long delayTime;
    /**
     * 到期时间
     */
    private final long expire;
    /**
     *  任务内容
     */
    private String content;

    public DelayTask(long delayTime, String content) {
        this.delayTime = delayTime;
        this.expire = System.currentTimeMillis() + delayTime;
        this.content = content;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public long getExpire() {
        return expire;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DelayTask{" +
                "delayTime=" + delayTime +
                ", expire=" + expire +
                ", content='" + content + '\'' +
                '}';
    }

    /**
     * 剩余时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
}
