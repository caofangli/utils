package com.cfl.oneself.utils.inter.interviewjvm2;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * OOM的种类
 *      java.lang.StackOverflowError
 *      java.lang.OutOfMemoryError:java heap space
 *      java.lang.OutOfMemoryError:GC overhead limit exceeded
 *      java.lang.OutOfMemoryError:Direct buffer memory
 *      java.lang.OutOfMemoryError:unable to create new native thread
 *      java.lang.OutOfMemoryError:Metaspace
 */
public class FPhantomReferenceDemo {
    public static void main(String[] args){
        Object o1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("---------------------------------");

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
