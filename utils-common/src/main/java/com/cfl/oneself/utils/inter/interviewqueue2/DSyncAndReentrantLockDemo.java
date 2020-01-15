package com.cfl.oneself.utils.inter.interviewqueue2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName： DSyncAndReentrantLockDemo
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 18:12 2019/12/17
 * @Vesion 1.0
 */
public class DSyncAndReentrantLockDemo {
    public static void main(String[] args){
        ShareResource shareResource = new ShareResource();
        new Thread(() ->{
            for (int i = 0;i<10;i++){
                shareResource.print5();
            }
        }, "AA").start();

        new Thread(() ->{
            for (int i = 0;i<10;i++){
                shareResource.print10();
            }
        }, "BB").start();

        new Thread(() ->{
            for (int i = 0;i<10;i++){
                shareResource.print15();
            }
        }, "CC").start();
    }
}

class ShareResource {
    private Integer number = 1;//A,B,C,D
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            //判断
            while(number !=1){
                c1.await();
            }
            //执行
            for (int i = 0; i <5;i++){
                System.out.println(Thread.currentThread().getName()+"\t" +i);
            }
            //通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            //判断
            while(number !=2){
                c2.await();
            }
            //执行
            for (int i = 0; i <10;i++){
                System.out.println(Thread.currentThread().getName()+"\t" +i);
            }
            //通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            //判断
            while(number !=3){
                c3.await();
            }
            //执行
            for (int i = 0; i <15;i++){
                System.out.println(Thread.currentThread().getName()+"\t" +i);
            }
            //通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
