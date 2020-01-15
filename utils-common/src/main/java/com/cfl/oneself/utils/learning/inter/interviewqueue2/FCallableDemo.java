package com.cfl.oneself.utils.learning.inter.interviewqueue2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * callable 和 runnable的区别
 * 前者有返回值，后者没有
 * 前者需要实现run方法，后者实现call方法
 * 一个主线程，一个子线程
 */
public class FCallableDemo {

    public static void main(String[] args) throws Exception{
        FutureTask<Integer> futureTask = new FutureTask<>(new FMyThread2());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new FMyThread2());

        new Thread(futureTask, "AA").start();
        new Thread(futureTask2, "BB").start();
        //如果启动线程后马上就要结果，主线程会阻塞在这里的
        //int result02 = futureTask.get();

        //如果线程执行内容没执行完成的话，自旋锁的概念。
        while (!futureTask.isDone()) {

        }
        int result01 = 100;
        //如无必要，放在逻辑处理最后
        // 因为如果放在中间，当子线程逻辑没有处理完的话，主线程会阻塞
        int result02 = futureTask.get();
        System.out.println(result01+result02);
    }
}


class FMyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+ "***************************come in callable");
        return 1024;
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

