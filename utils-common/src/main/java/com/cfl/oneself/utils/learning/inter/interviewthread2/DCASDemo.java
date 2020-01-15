package com.cfl.oneself.utils.learning.inter.interviewthread2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName： DCASDemo
 * @Description： CAS比较并交换
 * @Author： cfl
 * @Date: Created in 13:03 2019/12/15
 * @Vesion 1.0
 */
public class DCASDemo {
    public static void main(String[] args){
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // 传两个参数 期望值和更改值，判断期望值和内存中是否相同。
        // 线程会拷贝变量的副本，通过副本和主物理内存中的值比较，相同则更改，不同则不动。
        // 底层用的unsafe类通过do while 的方式去内存拿值拷贝副本判断，在执行操作逻辑比如++操作，不成功就一直执行，长时间do while会造成cpu开销变大
        System.out.println(atomicInteger.compareAndSet(5,2019)+ "\t current data:" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,2012)+ "\t current data:" + atomicInteger.get());
    }
}
