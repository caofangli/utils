package com.cfl.oneself.utils.learning.inter.interviewlock2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName： ReenterLockDemo
 * @Description： 可重入锁，可以锁中可以进入其他锁的方式（会自动获取内层锁），释放锁的次数也要一样
 *                 ReentrantLock(lock)和synchronized都是典型的可重入锁
 *                自旋锁：Unsafe类+CAS思想（自旋锁）
 *                是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗cpu
 * @Author： cfl
 * @Date: Created in 16:23 2019/12/15
 * @Vesion 1.0
 */
public class BReenterLockDemo {
    public static void main(String[] args){
        Phone phone = new Phone();
        new Thread(() ->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() ->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        Thread thread = new Thread(phone);
        Thread thread1 = new Thread(phone);
        thread.start();
        thread1.start();
    }
}

class Phone implements Runnable {
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+ "invoked sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+ "invoked sendEmail");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run () {
        get();
    }
    public void get(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+ "invoked get");
            set();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+ "invoked set");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
