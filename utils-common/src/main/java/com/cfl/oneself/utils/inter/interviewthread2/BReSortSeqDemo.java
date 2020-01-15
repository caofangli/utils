package com.cfl.oneself.utils.inter.interviewthread2;

/**
 * @ClassName： BReSortSeqDemo
 * @Description： 指令重排错误演示
 * @Author： cfl
 * @Date: Created in 12:01 2019/12/15
 * @Vesion 1.0
 */
public class BReSortSeqDemo {
    static int a = 0;
    static boolean flag = false;

    public static void method1(){
        a = 1;
        flag = true;
    }
    // 多线程环境中程序交替执行，由于编译器优化重排的存在（指令重排）
    // 两个线程中使用的变量能否保证一致性是无法确定的，导致最终处理结果的不同
    // 使用volatile关键字禁止指令重排
    public static void method2(){
        if(flag){
            a = a+5;
            System.out.println("ret value"+ a);
        }
    }

    public static void main(String[] args){
        for(int i = 1; i <= 10000; i++){
            new Thread(() ->{
                method1();
                method2();
            }, String.valueOf(i)).start();
        }
    }
}
