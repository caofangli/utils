package com.cfl.oneself.utils.inter.interviewlock2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName： FCyclicBarrierDemo
 * @Description： 等其他线程都执行完毕才能执行的线程CountDownLatch，完成一个加一，为自己设置的值时才执行
 * @Author： cfl
 * @Date: Created in 18:25 2019/12/15
 * @Vesion 1.0
 */
public class FCyclicBarrierDemo {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() ->{
            System.out.println("召唤神龙");
        });
        
        for(int i = 1; i <= 7; i++){
            int finalI = i;
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+ "收集到第" + finalI + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
