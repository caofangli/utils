package com.cfl.oneself.utils.learning.inter.interview1startorder;

import com.cfl.oneself.utils.learning.inter.interview1startorder.BaseCodeBlock;
import com.cfl.oneself.utils.learning.inter.interview1startorder.Other;

/**
 * 初始化子类会先初始化父类的内容
 * 一：类初始化
 *  1.父类static静态属性值。（顺序可和下边调换）
 *  2.父类static静态代码块。（顺序可和上边调换）
 *  3.子类static静态属性值。（顺序可和下边调换）
 *  4.子类static静态代码块。（顺序可和上边调换）
 * 二：类的实例初始化 （new对象调用类的<init>方法）
 *  1.super() 也就是先初始化父类里边的东西。
 *  2.父类的属性值      （顺序可和下边调换）
 *  3.父类的普通代码块   （顺序可和上边调换）
 *  4.父类的构造方法  4.5中间可能会有父类的普通方法执行。
 *  5.子类的属性值
 *  6.子类的普通代码块
 *  7.子类的构造方法
 *  8.子类的普通方法（override父类静态方法了）
 *  9.父类的静态方法
 *  10.子类的静态方法
 *  静态方法不能被重写，final的方法以及private的方法也不能被重写。
 */
public class CodeBlockForJava extends com.cfl.oneself.utils.learning.inter.interview1startorder.BaseCodeBlock {

    private static int j = method();
    private static int method() {
        System.out.println("子类的静态字段");
        return 1;
    }

    {
        System.out.println("这里是子类的普通代码块");
    }
    public CodeBlockForJava() {
        super();//在子类的构造器中一定会调用父类的构造方法，默认程序会给添加上。
        System.out.println("这里是子类的构造方法");
    }

    public void msg() {
        System.out.println("这里是子类的普通方法");
    }

    public static void msg2() {
        System.out.println("这里是子类的静态方法");
    }

    static {
        System.out.println("这里是子类的静态代码块");
    }

    public static void main(String[] args) {
        // 实例初始化
        BaseCodeBlock bcb = new CodeBlockForJava();
        bcb.msg();
        bcb.msg2();
        msg2();
    }
    com.cfl.oneself.utils.learning.inter.interview1startorder.Other o = new Other();
}
