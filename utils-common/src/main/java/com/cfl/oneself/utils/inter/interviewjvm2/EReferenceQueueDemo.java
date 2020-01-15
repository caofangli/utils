package com.cfl.oneself.utils.inter.interviewjvm2;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @ClassName： EReferenceQueueDemo
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 17:14 2019/12/22
 * @Vesion 1.0
 */
public class EReferenceQueueDemo {
    public static void main(String[] args){
        Object o1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("-------------------------------------------");

        o1 = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
