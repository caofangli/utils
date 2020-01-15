package com.cfl.oneself.utils.learning.inter.interviewlock2;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName： ECountDownLatchDemo
 * @Description： 等其他线程都执行完毕才能执行的线程CountDownLatch，完成一个减一，为0才执行
 * @Author： cfl
 * @Date: Created in 18:04 2019/12/15
 * @Vesion 1.0
 */
public class ECountDownLatchDemo {

    public static void main(String[] args) throws Exception{

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for(int i = 1; i <= 10; i++){
            int finalI = i;
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+finalI +"离开教室了");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"最后走的班长关门");
    }
}
