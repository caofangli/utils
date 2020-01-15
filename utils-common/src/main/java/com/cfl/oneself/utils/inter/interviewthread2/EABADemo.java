package com.cfl.oneself.utils.inter.interviewthread2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName： EABADemo
 * @Description： ABA问题 问题演示及解决
 * @Author： cfl
 * @Date: Created in 14:43 2019/12/15
 * @Vesion 1.0
 */
public class EABADemo {
    //有ABA问题的类方法
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    //解决ABA问题的类方法,加入版本号的参数
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args){
        System.out.println("以下是ABA问题的产生");
        new Thread(() ->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        }, "t1").start();
        new Thread(() ->{
            //线程暂停一秒钟,让上边的内容先执行完毕
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100,102) + "\t" + atomicReference.get().toString());
            
        }, "t2").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("以下是ABA问题的解决");
        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+ "第一次版本号为" +stamp);
            //线程暂停一秒钟,让线程4拿到和线程三一样的版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+ "第二次版本号为" +atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+ "第三次版本号为" +atomicStampedReference.getStamp());
        }, "t3").start();
        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+ "第一次版本号为" +stamp);
            // 让线程三有足够的时间完成一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100,2019,stamp,atomicStampedReference.getStamp()+1));
            System.out.println("当前最新的版本号" + atomicStampedReference.getStamp()+ "实际值" + atomicStampedReference.getReference());

        }, "t4").start();
    }
}
