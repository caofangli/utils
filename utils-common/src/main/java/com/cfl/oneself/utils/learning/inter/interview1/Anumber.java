package com.cfl.oneself.utils.learning.inter.interview1;

/**
 * 给基本类型赋值，传递的是数值。
 * 给引用类型赋值，传递的是地址值。
 * string和包装类的值是不可变的，只会在堆或常量池产生新的对象。
 */
public class Anumber {
    public static void main(String[] args) {
        int i = 1;
        // 赋值给i后+1再赋值，i变回1。
        i = i++;
        // 先赋值，后+1，i=2
        int j = i++;
        // 等号右边第一个i=2，第二个i先+1=3，第三个先算数后++，3*3=9再+第一个i 2 = 11，i最后+1=4。
        int k = i + ++i * i++;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }
}
