package com.cfl.oneself.utils.inter.interviewjvm2;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 软引用的map
 */
public class DWeakHashMapDemo {
    public static void main(String[] args){
        hashMapDemo();
        System.out.println("-----------------------------------------------");
        weakHashMapDemo();
    }
    public static void hashMapDemo() {
        Map<Object, Object> map = new HashMap<>();
        map.put(1,"hashMap");
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }

    public static void weakHashMapDemo() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = 1;
        String value = "hashMap";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }
}
