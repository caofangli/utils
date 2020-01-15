package com.cfl.oneself.utils.learning.inter.interviewjvm2;

/** 面试68课
 * jps -l 在java/bin目录下执行，查找运行的进程的进程号
 * jinfo -flag PrintGCDetails 6576  查看是否开启打印GC收集细节。
 *      -XX:+PrintGCDetails 设置开启打印GC收集细节。（在启动的地方的configurations里的 VM options中添加）（ + 开启 - 关闭）
 * jinfo -flag MetaspaceSize 6576  查看进程占用的内存大小
 *      -XX:MetaspaceSize=1024m  设置进程占用的内存大小
 *  jinfo -flags 6576 查询进程所有的参数列表
 *   -Xms 等价于 -XX:InitialHeapSize   值和下边参数要配成一样的
 *   -Xmx 等价于 -XX:MaxHeapSize       值和上边参数要配成一样的
 *
 *  java -XX:+PrintFlagsInitial  查看（jvm）初始化的时候的所有参数（根据机器性能自动配置的参数）
 *  java -XX:+PrintFlagsFinal  查看（jvm）自定义修改的包括初始化的所有参数（普通等号，没有被更改过，:= 表示用户或者jvm修改过的值）
 *      java -XX:PrintFlagsFinal -Xss128k T T代表jvm运行的类名，改变Xss到128运行jvm
 *  java -XX:PrintCommandLineFlags -version 可以查看常用参数及值，重要是能查看GC默认的垃圾回收器种类。
 *
 * jvm常用参数
 *      -Xms 初始内存大小，默认为物理内存1/64，等价于 -XX:InitialHeapSize
 *      -Xmx 最大内存大小，默认为物理内存1/4，等价于 -XX:MaxHeapSize
 *      -Xss 设置单个线程栈的大小，一般默认为512k-1024k，等价于 -XX:ThreadStackSize
 *      -Xmn  设置年轻代的大小
 *      -XX:MetaspaceSize 设置元空间大小，和永久代是一个意思，大小受本地内存限制。（使用本地内存）（默认21M，需要调整防止OOM异常）内存泄漏OOM
 *      -XX:PrintGCDetails 设置是否开启打印GC收集细节。
 *      -XX:SurvivorRatio=4 （默认是8：1：1） 设置新生代中eden和S0于S1的空间比例，
 *          新生区，约为总内存的三分之一中的8/10，from和to各占1/10（代表S0和S1）
 *          养老区，约为内存的三分之二
 *      -XX:NewRatio=2  （默认2大约是1：2） 配置新生代和老年代的占内存比例 新生代1，老年代2
 *      -XX:MaxTenuringThreshold=15 （默认15，最高15） 设置垃圾的最大年龄（进入养老代的次数）
 *
 *  强引用，软引用，弱引用，虚引用
 *      Book b1 = new Book(); 左边引用，存在栈区，右边对象，在堆里面 95%是强引用
 *          强引用：当内存不足时，jvm开始垃圾回收，对于强引用，死都不回收（导致OOM程序挂掉） 95%对象是强引用。（内存泄漏OOM）
 *          软引用：内存足够的时候不回收，不够的时候会回收
 */
public class BHelloGC {
    public static void main(String[] args){
        System.out.println("hello");
        //查看运行的时候的jvm的Xms和Xmx的大小值
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("(-Xms)="+totalMemory);
        System.out.println("(-Xmx)="+maxMemory);

//        try {
//            Thread.sleep(Integer.MAX_VALUE);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // 设置内存为10m，故意new一个50m的byte数组，撑爆内存。
        // 启动处配置：-Xms10m -Xmx10m -XX:+PrintGCDetails
        // 并且设置查看堆栈信息，
        // [GC (Allocation Failure) 新生区，约为总内存的三分之一中的8/10，from和to各占1/10
        // [Full GC (Allocation Failure) 养老区，约为内存的三分之二
        //
        byte[] bytes = new byte[50 * 1024 * 1024];
    }
}
