package com.cfl.oneself.utils.inter.interviewqueue2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：一个初始值为0的变量，两个线程一个加一一个减一，来五轮
 * 1.线程操作资源类
 * 2.判断，干活，通知（不能用if判断会造成虚假唤醒（4线程），要用while）
 * 传统版多线程生产者消费者模式代码
 * 3.防止虚假唤醒
 * synchronized 和lock的区别
 *   1.synchronized属于java的关键字（JVM层面），lock属于api层面。
 *      monitorenter（底层通过monitor对象来完成），wait和nnotify也依赖于monitor对象，进入
 *      monitorexit 退出（会有两次退出，一种正常退出，一种异常退出，无论如何都会退出，不会产生死锁）
 *   lock：是具体类，api层面的锁
 *   2.synchronized不需要用户手动释放
 *     lock需要手动释放，会产生死锁。
 *   3.等待是否可以中断
 *      synchronized不可以被中断
 *      lock可以设置超时中断。可以lockInterruptibly()放代码块中，调用interrupt()方法可中断。
 *   4.加锁是否公平
 *      synchronized默认非公平锁。
 *      lock两者都可以，默认非公平锁。构造方法可以传值设置成公平锁。
 *   5.锁绑定多个条件的condition
 *      synchronized无condition
 *      lock用condition来实现分组唤醒需要唤醒的线程组，可以精确唤醒，而不像synchronized，要么随机唤醒一个，要么都唤醒，更加灵活
 */
public class CProdConsumer_TraditionDemo {

    public static void main(String[] args){
        ShareData shareData = new ShareData();
        new Thread(() ->{
            for (int i =1; i <=5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AAA").start();
        new Thread(() ->{
            for (int i =1; i <=5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BBB").start();

        new Thread(() ->{
            for (int i =1; i <=5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "CCC").start();
        new Thread(() ->{
            for (int i =1; i <=5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "DDD").start();
    }

}

class ShareData {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try{
            //1.判断线程的停止，不能用if判断会造成虚假唤醒（4线程），要用while
            while (number != 0) {
                //线程停止。等待不能生产
                condition.await();
            }
            //2.线程执行++，干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t " + number);
            //3.通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try{
            //1.判断线程的停止
            while (number == 0) {
                //线程停止。等待不能生产
                condition.await();
            }
            //2.线程执行--，干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t " + number);
            //3.通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
