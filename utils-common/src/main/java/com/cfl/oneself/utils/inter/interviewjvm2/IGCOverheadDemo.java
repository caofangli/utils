package com.cfl.oneself.utils.inter.interviewjvm2;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError:GC overhead limit exceeded
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 超过98%的时间用来做GC并且回收了不到2%的堆内存，很快堆内存又不够用，迫使gc再次执行，这样恶行循环，导致cpu一直居高不下
 * 干脆抛异常，让工程师解决。
 */
public class IGCOverheadDemo {
    public static void main(String[] args){
        int i =0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println("*******************i="+i);
            e.printStackTrace();
            throw e;
        }
        //java.lang.OutOfMemoryError: GC overhead limit exceeded
    }
}
