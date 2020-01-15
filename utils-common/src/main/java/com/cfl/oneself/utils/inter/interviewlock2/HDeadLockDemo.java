package com.cfl.oneself.utils.inter.interviewlock2;

import java.util.concurrent.TimeUnit;

/**
 * 死锁，两个线程因为争抢资源造成的一种互相等待的现象，造成无外力就无法进行下去的状态。
 *
 */
public class HDeadLockDemo {
    public static void main(String[] args){
        String lock1 = "lock1";
        String lock2 = "lock2";
        new Thread(new HoldLockThread(lock1,lock2), "AAA").start();
        new Thread(new HoldLockThread(lock2,lock1), "BBB").start();

        /**
         * linux中查看进程命令：ps -ef|grep xxx
         * java中：jps   jps -l 查询出现异常的线程的线程编号
         * 用线程编号查看java中的堆栈信息，jstack查看堆栈报错信息。
         */
    }
}

class HoldLockThread implements Runnable {

    private String lock1;
    private String lock2;

    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName()+"\t自己持有"+lock1+"锁\t尝试获得"+lock2);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName()+"\t自己持有"+lock2+"锁\t尝试获得"+lock1);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
