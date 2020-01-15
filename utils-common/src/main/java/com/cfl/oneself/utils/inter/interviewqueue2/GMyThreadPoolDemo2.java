package com.cfl.oneself.utils.inter.interviewqueue2;

import java.util.concurrent.*;

/**
 * 如何合理的配置线程池
 *      先了解计算机的核数，Runtime.getRuntime().availableProcessors()
 *      cpu密集型业务，wile（true）
 *          核数+1的核心线程数。
 *      io密集型业务，类似mysql取数据，取磁盘数据
 *          cpu核数*2。
 *          核数/（1-0.9）的结果，比如8核就是80个线程。
 *
 */
public class GMyThreadPoolDemo2 {
    public static void main(String[] args){
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        //拒绝策略:ThreadPoolExecutor.AbortPolicy() 报异常
        //拒绝策略:ThreadPoolExecutor.CallerRunsPolicy() 把任务回退给调用者处理，会将执行任务的负责人变成父线程处理。（main线程）

        try {
            for(int i = 1; i <= 10; i++){
                int finalI = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t办理业务" + finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        /**
         * 死锁，两个线程因为争抢资源造成的一种互相等待的现象，造成无外力就无法进行下去的状态。
         */
    }
}
