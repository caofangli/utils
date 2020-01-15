package com.cfl.oneself.utils.inter.interviewthread2;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName： AVolatileDemo
 * @Description： 1.验证volatile的可见性
 * 1.1假如int number = 0; number添加volatile关键字之前没有可见性
 * 1.2 添加volatile关键字后，有可见性
 * 2.验证volatile不保证原子性
 *      不可分割，程序执行的完整性，中间不可以被加塞或者被分割，需要整体完整。
 *      要么同时成功，要么同时失败。
 *
 * @Author： cfl
 * @Date: Created in 10:51 2019/12/15
 * @Vesion 1.0
 */
public class AVolatileDemo {
    public static void main(String[] args){
        MyData myData = new MyData();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add();
            System.out.println(Thread.currentThread().getName() + "update number value:" + myData.number);
        }, "AAA").start();
        System.out.println("创建线程后等待吗?");
        while (myData.number == 0) {
        }
        // 不加volatile关键字的时候这句不打印，程序不结束
        System.out.println(Thread.currentThread().getName()+"\t mission is over "+myData.number);
    }
}

class MyData{
    volatile int number =0;

    public void add(){
        this.number = 60;
    }
}
