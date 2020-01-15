package com.cfl.oneself.utils.inter.interviewqueue2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第一种extends Thread
 * 第二种Runnable
 * 第三种callable
 * 第四种使用/获得java多线程的方式，线程池
 */
public class GMyThreadPoolDemo {
    public static void main(String[] args){
        //查看电脑处理器核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        //数  组Array Arrays
        //集合类collection collections
        //线程池executor executors
        //常用类的对应辅助工具类

        //线程池中能存在固定个数个线程的线程池
        //底层是linkedBlockingQueue阻塞队列
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //线程池中只能存在一个线程的线程池
        //底层是linkedBlockingQueue阻塞队列
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        //线程池中的线程数是n个，可以缓存的线程池，个数看处理情况。
        //底层是SynchronousQueue
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        /**
         * 线程池参数含义
         *     public ThreadPoolExecutor(int corePoolSize,
         *                               int maximumPoolSize,
         *                               long keepAliveTime,
         *                               TimeUnit unit,
         *                               BlockingQueue<Runnable> workQueue,
         *                               ThreadFactory threadFactory,
         *                               RejectedExecutionHandler handler) {
         *      1.corePoolSize：线程池核心线程数（银行今日当值窗口：今日有人值班的窗口）
         *      2.maximumPoolSize：线程池最大的线程数（银行物理窗口上限，加上可以临时加班的人员的窗口数）
         *      5.BlockingQueue：阻塞队列（银行里边的候客区）
         *      6.ThreadFactory：线程工厂，默认就好（银行网点的logo，工作人员的制服/胸卡等）
         *      7.RejectedExecutionHandler：拒绝策略，拒绝后边的全部请求（银行办理窗口和候客区都满人了，为防止踩踏事件，大堂经理的拒客方式）
         *          策略1.AbortPolicy(默认)，直接抛出RejectedExecutionException异常组织系统正常运行。
         *          策略2.CallerRunsPolicy，把任务回退给调用者处理，会将执行任务的负责人变成父线程处理。（main线程）
         *          策略3.DiscardOldestPolicy，不处理？
         *          策略4.DiscardPolicy，直接丢弃任务，不予任何处理也不抛出异常
         *      3.keepAliveTime：（业务量下降后，办理业务数用不到加班窗口的线程的时候，设定一个超时时间，超时后，加班窗口线程会自动销毁，直到业务窗口变成和corePoolSize原始值一样）
         *      4.unit：加班线程的空闲销毁时间类型设定参数
         *  线程池底层原理：
         *      业务线程来2个直接进corePoolSize里边处理，再来进阻塞队列等候
         *      阻塞队列满啦之后，开启maximumPoolSize减去corePoolSize的线程，进行处理，再来继续进阻塞队列等候。
         *      还来，开启拒绝策略。
         *      流量下降后，通过设置的keepAliveTime和unit超时时间，逐渐关闭maximumPoolSize减去corePoolSize的加班线程，直至只有corePoolSize存在。
         *
         *      1.在创建了线程池后，等待提交过来的任务请求。
         *      2.当调用execute()方法添加一个请求任务时，线程池会做如下判断。
         *          2.1.如果正在运行的线程数量小于 corePoolSize ，那么马上创建线程运行这个任务。
         *          2.2.如果正在运行的线程数量大于等于 corePoolSize ，那么将这个任务放入阻塞队列里等待。
         *          2.3.如果这时候队列满了且正在运行的线程数量h还小于 maximumPoolSize ，那么还是要创建非核心线程立刻运行这个任务。
         *          2.4.如果队列满了且正在运行的线程数量大于等于 maximumPoolSize ，那么线程池会自动启动饱和拒绝策略来执行。
         *      3.当一个线程完成任务时，它会从队列中取下一个任务来执行。
         *      4.当一个线程无事可做超过一定时间 keepAliveTime ，线程池会判断。
         *          4.1.如果当前运行的线程池数大于 corePoolSize ，那么这个线程就被停掉。
         *          4.2.所以线程池的所有任务完成后它最终会收缩到 corePoolSize 的大小。
         *  真正的生产中不能创建已有的线程池，（Executors），因为其底层是 LinkedBlockingQueue 阻塞队列长度是21亿多，堆积大量请求，会造成OOM，资源被消耗空。使用线程池
         *  要自己创建 ThreadPoolExecutor 设置七大参数。
         */

        try {
            for(int i = 1; i <= 200; i++){
                int finalI = i;
                executorService2.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t办理业务" + finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            executorService1.shutdown();
            executorService2.shutdown();
        }

    }
}
