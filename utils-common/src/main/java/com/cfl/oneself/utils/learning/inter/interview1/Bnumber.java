package com.cfl.oneself.utils.learning.inter.interview1;

/**
 * @ClassName Bnumber
 * @Description TODO:
 * @Author cfl
 * @Date 2020/1/4 12:59
 * @Version 1.0
 */
public class Bnumber {
    static int s;
    int i;
    int j;
    {
        int i = 1;
        i++;//就近原则，指的是代码块中的i，加上this等于类的属性i
        j++;//等于this.j++
        s++;//等于this.s++
    }
    public void test (int j) {
        j++;//就近原则，指的是参数中的j，加上this等于类的属性j
        i++;
        s++;
    }
    public static void main(String[] args) {
        Bnumber bnumber1 = new Bnumber();//new对象，操作一遍普通代码块{}中的内容，其中包括static的s和j
        Bnumber bnumber2 = new Bnumber();//new对象，操作一遍普通代码块{}中的内容，其中包括static的s和j
        bnumber1.test(10);//操作i和s
        bnumber1.test(20);//操作i和s
        bnumber2.test(30);//操作i和s
        System.out.println(bnumber1.i+","+bnumber1.j+","+bnumber1.s);
        System.out.println(bnumber2.i+","+bnumber2.j+","+bnumber2.s);
    }
}
