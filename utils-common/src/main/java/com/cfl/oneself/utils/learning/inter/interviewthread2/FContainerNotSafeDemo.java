package com.cfl.oneself.utils.learning.inter.interviewthread2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName： FContainerNotSafeDemo
 * @Description： list线程非安全的演示
 * @Author： cfl
 * @Date: Created in 15:15 2019/12/15
 * @Vesion 1.0
 */
public class FContainerNotSafeDemo {
    public static void main(String[] args){

        List<String> list = new ArrayList<>();

        for(int i = 1; i <= 10; i++){
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException  arrayList线程非安全的报错内容
        /**
         * 故障现象 java.util.ConcurrentModificationException  arrayList
         * 导致原因,线程非安全类
         * 解决方案
         *      1.new Vector();
         *      2.Collections.synchronizedList(new ArrayList<>());
         *      3.new CopyOnWriteArrayList<>();
         *      这种实现方式是利用读写分离的思想,读的一份共通读,写的先copy一份出来,改完后覆盖原先的,子啊写的过程中加锁()lock的方式
         *      lock.lock();加锁  lock.unlock();解锁
         */
        List<Object> objects = Collections.synchronizedList(new ArrayList<>());

        //list的线程安全所用的最好方法
        CopyOnWriteArrayList<Object> objects1 = new CopyOnWriteArrayList<>();
        for(int i = 1; i <= 10; i++){
            new Thread(() ->{
                objects1.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(objects1);
            }, String.valueOf(i)).start();
        }
        /**
         * set 和map 的对应和list一样的线程安全的方法类
         * new CopyOnWriteArraySet<>();底层对象还是CopyOnWriteArrayList //set的线程安全所用的最好方法
         * HashSet底层就是HashMap set的键值属性,值是固定的而已
         *  new ConcurrentHashMap<>();  //map的线程安全所用的最好方法
         */
        //set的线程安全所用的最好方法
        CopyOnWriteArraySet<Object> objects2 = new CopyOnWriteArraySet<>();
        for(int i = 1; i <= 10; i++){
            new Thread(() ->{
                objects2.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(objects1);
            }, String.valueOf(i)).start();
        }
        //map的线程安全所用的最好方法
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        for(int i = 1; i <= 10; i++){
            int finalI = i;
            new Thread(() ->{
                objectObjectConcurrentHashMap.put(finalI +"", UUID.randomUUID().toString().substring(0,8));
                System.out.println(objects1);
            }, String.valueOf(i)).start();
        }
    }
}
