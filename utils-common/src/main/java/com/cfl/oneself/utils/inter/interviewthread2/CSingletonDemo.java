package com.cfl.oneself.utils.inter.interviewthread2;

/**
 * @ClassName： CSingletonDemo
 * @Description： 多线程单例模式
 *                  多线程情况下单例模式的对象被初始化了多次
 * @Author： cfl
 * @Date: Created in 12:14 2019/12/15
 * @Vesion 1.0
 */
public class CSingletonDemo {
    //加锁之后 需要加volatile关键字禁止指令重排
    private static volatile CSingletonDemo instance = null;
    private CSingletonDemo() {
        System.out.println(Thread.currentThread().getName()+ "我是构造方法");
    }
    //解决方法1 整个方法加锁public synchronized static CSingletonDemo getInstance() {  影响效率
    // 解决办法入下，在if判断后加锁方式多次初始化，此并不能解决在new的过程中造成的指令重排的问题。
    //  1.先分配对象的内存地址
    //  2.初始化对象
    //  3.设置instance指向刚分配的内存地址，此时instance!=null
    //  指令重排导致23过程相反的话就会报错一千万之一的几率
    public static CSingletonDemo getInstance() {
        if (instance==null){
            synchronized (CSingletonDemo.class){
                if(instance==null) {
                    instance =new CSingletonDemo();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args){

//        System.out.println(CSingletonDemo.getInstance() == CSingletonDemo.getInstance());
//        System.out.println(CSingletonDemo.getInstance() == CSingletonDemo.getInstance());
//        System.out.println(CSingletonDemo.getInstance() == CSingletonDemo.getInstance());

        for(int i = 1; i <= 10; i++){
            new Thread(() ->{
                CSingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }

}
