package com.cfl.oneself.utils.inter.interviewqueue2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName： ABlockingQueueDemo
 * @Description： ArrayBlockingQueue  专属定制版：产生一个消费一个（消息）
 * @Author： cfl
 * @Date: Created in 19:24 2019/12/15
 * @Vesion 1.0
 */
public class ABlockingQueueDemo {
    public static void main(String[] args){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // 异常组：因为线程池的长度只有3，当加入第四个元素的时候因为线程池长度超过阈值，所以报错
        System.out.println(blockingQueue.add("d"));
        //异常组：remove和element（检查顶端元素）是相同的效果。

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // 特殊值组：当超过线程池长度的时候，会返回false信息，告知未操作成功
        System.out.println(blockingQueue.offer("d"));
        // 特殊值组：poll和peek（检查顶端元素）是相同的效果，取失败的话返回值时null

        // 阻塞组：没有位置的时候一直等待，直到有位置的时候再执行操作。
        // tack的作用一样，相当于买不到蛋糕就不走了，一直等待做好蛋糕。
        try {
            blockingQueue.put("a");
            blockingQueue.put("b");
            blockingQueue.put("c");
            blockingQueue.put("d");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 超时组：没有位置的时候一直等待，直到有位置的时候再操作或者过了超时时间就会放弃。返回是否成功的标识
        try {
            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
