package com.cfl.oneself.utils.learning.inter.interviewjvm2;

/**
 * java.lang.OutOfMemoryError:Direct buffer memory
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * ByteBuffer.allocate(capability)第一种是分配JVM堆内存，属于GC管辖，可以进行回收
 * ByteBuffer.allocteDirect(capability)第二种是分配OS本地内存，不属于GC管辖，不能进行回收。
 * 如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC操作，DirectByteBuffer对象就不会被回收（NIO程序）
 * 这时堆内存充足，但本地内存已经使用完了的话，再次尝试分配本地内存就会出现OutOfMemoryError,程序直接挂掉
 */
public class JDirectBufferMemoryDemo {
    public static void main(String[] args){
        System.out.println("配置的MaxDirectMemorySize:"+(sun.misc.VM.maxDirectMemory() / (double)1024/1024)+"MB");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ByteBuffer bb = new ByteBuffer.allocateDirect(6*1024*1024);
    }
}
