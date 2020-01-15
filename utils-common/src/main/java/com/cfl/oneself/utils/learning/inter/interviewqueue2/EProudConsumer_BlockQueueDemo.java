package com.cfl.oneself.utils.learning.inter.interviewqueue2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName： EProudConsumer_BlockQueueDemo
 * @Description： 生产者消费者模式（综合练习）
 * @Author： cfl
 * @Date: Created in 8:34 2019/12/18
 * @Vesion 1.0
 */
public class EProudConsumer_BlockQueueDemo {
    public static void main(String[] args){
        MyResource myResource =new MyResource(new ArrayBlockingQueue(10));
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "prod").start();

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("五秒时间到，大BOOS叫停");
        myResource.stop();
    }
}

class MyResource {
    //默认开启进行生产和消费。
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue blockingQueue = null;

    //类构造方法，初始化调用一次，参数为blockingQueue，可以适配所有阻塞队列类型，增强程序的扩展性。
    public MyResource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean reValue;
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";//i++操作
            reValue = blockingQueue.offer(data,2L,TimeUnit.SECONDS);
            if (reValue) {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列成功" + data);
            } else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列失败" + data);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫停了，表示flag=false了，生产动作结束");
    }

    public void myConsumer() throws Exception {
        Object reValue = null;
        while (flag) {
            reValue = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(null == reValue || reValue.equals("")) {
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t超过两秒无数据，消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费队列成功" + reValue);
        }
    }

    public void stop(){
        this.flag = false;
    }
}
