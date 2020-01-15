package com.cfl.oneself.utils.learning.inter.interviewqueue2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列，消费皇后才能添加到队列
 */
public class BSynchronousQueueDemo {
    public static void main(String[] args){
        BlockingQueue blockingQueue = new SynchronousQueue();

        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName()+ "put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+ "put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+ "put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "BBB").start();

    }
}
