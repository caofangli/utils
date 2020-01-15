package com.cfl.oneself.utils.inter.interviewthread2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName： AVolatileDemo
 * @Description： 1.验证volatile的可见性
 * 1.1假如int number = 0; number添加volatile关键字之前没有可见性
 * 1.2 添加volatile关键字后，有可见性
 * 2.验证volatile不保证原子性
 *      不可分割，程序执行的完整性，中间不可以被加塞或者被分割，需要整体完整。
 *      要么同时成功，要么同时失败。
 *   解决用原子变量
 *      直接用对应类型的AtomicInteger类可解决原子性
 *      浪费性能做法时加synchronized关键字
 *
 * @Author： cfl
 * @Date: Created in 10:51 2019/12/15
 * @Vesion 1.0
 */
public class AVolatileDemo2 {
    public static void main(String[] args){
        MyData2 myData = new MyData2();
        for(int i = 1; i <= 20; i++){
            new Thread(() ->{
                for (int j = 1; j <= 1000; j++){
                    myData.addOne();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        //需要等待上边所有线程都执行完毕，才能查看最终的计算结果是多少。
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // 查看程序正在运行的进程个数,如果没执行完就让其执行。
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName()+"finally number value:" + myData.atomicInteger);
    }
}

class MyData2{
    volatile int number =0;

    public void addOne(){
        number++;
    }

    //解决原子性问题
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic(){
        //++操作
        atomicInteger.getAndIncrement();
    }
}
