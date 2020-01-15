package com.cfl.oneself.utils.inter.interviewjvm2;

/**
 * OOM的种类
 *      java.lang.StackOverflowError
 *          栈溢出，被撑爆了。属于错误（方法在栈）
 *      java.lang.OutOfMemoryError:java heap space
 *          堆内存不够用了，属于错误（对象在堆）
 *      java.lang.OutOfMemoryError:GC overhead limit exceeded
 *          内存急剧上升，大量资源用来做gc，少量做运算且gc效果很低（只回收了2%的内存），gc回收时间过长
 *      java.lang.OutOfMemoryError:Direct buffer memory
 *
 *      java.lang.OutOfMemoryError:unable to create new native thread
 *
 *      java.lang.OutOfMemoryError:Metaspace
 *
 */
public class GStackOverflowErrorDemo {
    public static void main(String[] args){
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
    //Exception in thread "main" java.lang.StackOverflowError
}
