package com.cfl.oneself.utils.learning.inter.interview1startorder;

import com.cfl.oneself.utils.learning.inter.interview1startorder.Other2;

/**
 * @ClassName： BaseCodeBlock
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 17:38 2019/12/14
 * @Vesion 1.0
 */
public class BaseCodeBlock {

    private static int j = method();
    private static int method() {
        System.out.println("父类的静态字段");
        return 1;
    }

    public BaseCodeBlock() {
        System.out.println("这里是父类的构造方法");
    }

    public void msg() {
        System.out.println("这里是父类的普通方法");
    }

    public static void msg2() {
        System.out.println("这里是父类的静态方法");
    }

    static {
        System.out.println("这里是父类的静态代码块");
    }

    com.cfl.oneself.utils.learning.inter.interview1startorder.Other2 o2 = new Other2();

    {
        System.out.println("这里是父类的普通代码块");
    }
}
