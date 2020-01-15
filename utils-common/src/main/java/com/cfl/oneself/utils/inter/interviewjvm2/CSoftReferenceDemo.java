package com.cfl.oneself.utils.inter.interviewjvm2;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 *  强引用，软引用，弱引用，虚引用
 *      Book b1 = new Book(); 左边引用，存在栈区，右边对象，在堆里面 95%是强引用
 *          Reference 强引用：当内存不足时，jvm开始垃圾回收，对于强引用，死都不回收（导致OOM程序挂掉） 95%对象是强引用。（内存泄漏OOM）
 *          SoftReference 软引用：内存足够的时候不回收，不够的时候会回收
 *              需要缓存大量图片的情况，避免OOM又能加快图片读取
 *              Map<String,SoftReference<Bitmap>> imageCache = new Map<String,SoftReference<Bitmap>>();
 *          WeakReference 弱引用：gc不管内存够不够用都会回收
 *          PhantomReference虚引用（幽灵引用）持有虚引用的对象就和没有引用一样，任何时候都有可能被垃圾回收器回收，不能单独使用也不能通过他访问对象，必须和引用队列联合使用（ReferenceQueue）
 *          用于监控对象的回收状态，类似aop的后置通知？.当虚引用对象被gc回收后，会出现在引用队列里边（ReferenceQueue），可以在ReferenceQueue中对其进行操作，没被回收前，对象的get方法返回null
 *          可以参照 F PhantomReferenceDemo
 *
 */
public class CSoftReferenceDemo {

    public static void main(String[] args){
        //softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }

    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference);
    }

    /**
     * JVM配置：故意产生大对象并配置小内存，让内存不够用导致OOM，看软引用的回收情况。
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        try {
            byte[] bytes = new byte[30*1024*1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void WeakReferenceDemo() {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
