package com.cfl.oneself.utils.inter.interviewlock2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName： GSemaphoreDemo
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 19:06 2019/12/15
 * @Vesion 1.0
 */
public class GSemaphoreDemo {
    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(3);
        for(int i = 1; i <= 6; i++){
            new Thread(() ->{
                try{
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+ "抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+ "停三秒以后离开车位");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
