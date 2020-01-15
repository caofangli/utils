package com.cfl.oneself.utils.inter.interviewgc2;

import java.util.Random;

/**
 *  垃圾回收GC算法四种（最终是分代收集）新生代用复制，老年代用标记整理和标记删除
 *      1.引用计数，不用了，有引用+1，无引用-1，直到0为止回收
 *      2.复制，用于新生代，浪费空间，时间。
 *      3.标记清除，产生内存碎片。（这一块内存空，旁边一块有东西，乱七八糟的），用于老生代
 *      4.标记整理（标记清除整理），移动对象需要成本。用于老生代
 *  算法对应的垃圾回收器：（四种主要垃圾回收器）java8的
 *
 *      1.serial 串行：暂停所有运行的程序，执行单线程串行垃圾回收，然后再执行程序，不可取。
 *          -XX:UseSerialGC  （配置后，新生代：UseSerialGC，老年代：UseSerialOldGC）
 *      2.parallel 并行：暂停所有运行的程序，执行多线程串行垃圾回收，然后再执行程序，串行的加强版。性能好一些
 *          -XX:UseParallelGC  （配置后，新生代：UseParallelGC，老年代：UseParallelOldGC）（UseParallelGC是默认的垃圾回收器）
 *      3.CMS 并发：并发标记清除，交替执行，不需要暂停运行的程序，
 *          -XX:UseConcMarkSweepGC    并发标记删除GC   （配置后，新生代：UseParallelGC，老年代：[UseConcMarkSweepGC 备用是：UseParallelOldGC]）（互联网公司推荐使用）
 *          标记过程和确认标记过程也会暂停工作线程，非常短。（初始标记-并发-重新标记-并发清除），优点，并发收集停顿低，缺点，并发执行cpu压力大，会产生内存碎片。
 *      4.G1 ：将堆内存分割成不同的区域然后并发的对其进行垃圾回收（不会产生内存碎片，用户可以指定期望的垃圾回收时间，分区回收避免了全内存扫描）
 *          -XX:UseG1GC （初始标记，并发标记，最终标记，筛选回收）
 *              设置G1垃圾回收器的一些参数：
 *                  -XX:G1HeapRegionSize=n 设置G1的内存块大小1MB-32MB之间
 *                  -XX:MaxGCPauseMillis=n 设置G1回收的停顿时间
 *                  -XX:InitiatingHeapOccupancyPercent=n 设置G1并发时使用的线程数
 *                  -XX:G1ReservePercent=n 设置作为空闲空间的预留内存怒百分比，以降目标空间溢出的风险，默认是10%。
 *
 *
 *      查看程序默认的垃圾回收器：java -XX:PrintCommandLineFlags -version
 *          -XX:UseParNewGC   并行新生代GC   （配置后，新生代：UseParallelGC，老年代：UseSerialOldGC） （不推荐使用）
 *          -XX:UseParallelOldGC  并行老年代GC  （配置后，新生代：UseParallelGC，老年代：UseParallelOldGC）（UseParallelGC是默认的垃圾回收器）
 *
 *      查询服务或main线程用的是哪个GC
 *          jps找到进程编号
 *          jinfo -flag UseSerialGC 1111（进程编号）  查看是否用的串行GC
 *      年轻代回收方式：UseSerialGC copying，UseParallelGC，UseParNewGC，G1
 *      老年代回收方式：UseSerialOldGC(已废弃)，UseParallelOldGC，UseConcMarkSweepGC，G1
 *
 *      DefNew   ------------------  Default New Generation
 *      Tenured  ------------------  Old
 *      ParNew   ------------------  Parallel New Generation
 *      PSYoungGen ----------------  Parallel Scavenge
 *      ParOldGen  ----------------  Parallel Old Generation
 *
 *
 *
 *      在linux启动的时候的JVM+GC的配置参数调优
 *          1.java -server -Xms1024m -Xmx1024m -XX:+UseG1GC -jar springbootDemo.war
 *          2.查询进程启动状态
 *              jsp -l
 *              jinfo -flags 进程号
 *
 *      linux优化命令：
 *          1.整机top  所有进程的状态，cpu占用率，内存占用率等
 *              load average 后边跟三个负载值，一分钟，五分钟，十五分钟，的平均负载值。（平均值高于60%，代表负载压力大）s看每一个核的负载。
 *              1.2uptime 只查询 load average
 *          2.CPU：vmstat 查看CPU
 *              vmstat -n 2 3 隔两秒钟采样一次，共采样三次。
 *              procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 *              r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 *              1  0      0 1898652   2068 932056    0    0     2     1   47   87  0  0 100  0  0
 *              0  0      0 1898644   2068 932088    0    0     0     0  178  329  0  0 100  0  0
 *              0  0      0 1898628   2068 932088    0    0     0     0  185  356  0  0 100  0  0
 *          procs
 *              r：运行和等待CPU时间片的进程数，原则上y一核的CPU的运行队列不要超过2，整个系统的运行队列不能超过总核数的两倍
 *              b：等待资源的进程数，比如等待磁盘I/O，网络I/O等。
 *          cpu
 *              us：用户进程消耗cpu时间百分比，us值高，用户进程消耗cpu时间多，如果长期大于50%，优化进程
 *              sy：内核进程消耗cpu时间百分比。
 *              us + sy：参考值为80%，大于说明存在cpu不足。
 *              id：处于空闲的cpu百分比
 *              wa：系统等待IO的CPU时间百分比。
 *              st：来自于一个虚拟机偷取的CPU时间的百分比。
 *          mpstat -P ALL 2 查看所有核的cpu 的状态，两秒一次。
 *          3.内存：free -m 查看内存 用MB显示。保证其在20%到70%之间就可以。
 *              pidstat -p 进程号 -r 相隔时间  查询某进程占用的内存比例。
 *          4.硬盘：df -h 查看磁盘剩余空间
 *          5.磁盘IO：iostat -xdk 2 3
 *              rkB/s 每秒读取数据量kb
 *              wkB/s 每秒写入数据量kb
 *              svctm I/O 请求的平均服务时间，单位毫秒。
 *              await I/O 请求的平均等待时间，单位毫秒，值越小性能越好。
 *              util 一秒钟有百分之几的时间用于I/O操作，接近于100%的时候，标识磁盘宽带跑满，需要优化程序或者增加磁盘。
 *              rkB/s 和 wkB/s 长期，超大数据读写，肯定不正常，需要优化程序读取。
 *              svctm的值和await的值很接近，表示几乎没有I/O等待，磁盘性能好。
 *              如果await的值远高于svctm的值，则表示I/O队列等待时间太长，需要优化程序或者更换更快磁盘。
 *              pidstat -d 2 -p 进程号  每两秒查询一次线程占用磁盘情况。
 *          6.网络IO：ifstat l  网络负载情况。
 *
 *  CPU占用过高的解决方案和过程
 *      1.先用top命令找出CPU占比高的进程id。
 *      2.查找具体进程运行的类路径，jps -l  或者，ps -ef|grep java|grep -v grep
 *      3.定位到具体代码行，ps -mp 进程号 -o THREAD,tid,time   （定位到具体线程或代码）也就是获得tid。
 *      4.jstack 进程号 | grep tid（16进制线程ID英文字母小写） -A60
 *
 */
public class AGCDemo {
    // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:PrintCommandlineFlags -XX:UseSerialGC
    // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:PrintCommandlineFlags -XX:UseParNewGC
    // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:PrintCommandlineFlags -XX:UseParallelGC
    // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:PrintCommandlineFlags -XX:UseParallelOldGC
    // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:PrintCommandlineFlags -XX:UseSerialGC
    // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:PrintCommandlineFlags -XX:UseSerialGC
    // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:PrintCommandlineFlags -XX:UseSerialGC
    public static void main(String[] args){
        System.out.println("****************GCDemo start");
        try{
            String str = "hello";
            while (true) {
                str = str + new Random().nextInt(7777777)+new Random().nextInt(88888);
                str.intern();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
