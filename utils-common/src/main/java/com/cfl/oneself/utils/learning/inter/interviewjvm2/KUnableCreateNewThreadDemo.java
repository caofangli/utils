package com.cfl.oneself.utils.learning.inter.interviewjvm2;

/**
 * 高并发的程序中，会出现：java.lang.OutOfMemoryError:unable to create new native thread
 * 导致原因
 *      应用创建了太多的线程，一个应用创建了多个线程，超过系统承载极限。
 *      你的服务器不允许你的应用创建这么多的线程，linux承载默认允许单个进程可以创建的线程数是1024个。实际900多会挂，因为linux有自用线程
 * 解决办法：
 *      降低一个应用中创建的线程数量。
 *      更改linux中最大线程创建数的默认配置
 *      ulimit -u linux中查看当前用户能够创建的线程数，root用户线程数无上限。
 *      vim /etc/security/limits.d/90-nproc.conf  更改linux中默认的用户能够创建的最大线程数。
 */
public class KUnableCreateNewThreadDemo {
    public static void main(String[] args){
        for(int i = 1;  ; i++){
            System.out.println("--------------------------------------i=:"+i);
            new Thread(() ->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
