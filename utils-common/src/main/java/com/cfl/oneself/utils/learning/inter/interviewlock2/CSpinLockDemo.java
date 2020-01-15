package com.cfl.oneself.utils.learning.inter.interviewlock2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName： CSpinLockDemo
 * @Description： 用可重入锁和自旋锁的理念写自旋形式的lock
 * @Author： cfl
 * @Date: Created in 16:48 2019/12/15
 * @Vesion 1.0
 */
public class CSpinLockDemo {

    AtomicReference arf = new AtomicReference<Thread>();

    // 自己写的自旋lock锁，没用lock和synchronized
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"come in");

        while (!arf.compareAndSet(null, thread)){

        }
    }
    public void myUnlock(){
        Thread thread = Thread.currentThread();
        arf.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"invoked myunlock");
    }
    
    public static void main(String[] args){
        CSpinLockDemo CSpinLockDemo = new CSpinLockDemo();

        new Thread(() ->{
            CSpinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CSpinLockDemo.myUnlock();
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->{
            CSpinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CSpinLockDemo.myUnlock();
        }, "t2").start();
    }
}
