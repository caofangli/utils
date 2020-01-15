package com.cfl.oneself.utils.learning.inter.interviewlock2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName： DReadWriteLockDemo
 * @Description： 多个线程读一个资源类没问题，所以要弄读写分离，可同时多人读，只可一人写
 *                  读：能共享
 *                  写：原子 + 独占 ,写的时候，其操作步骤不能被分割
 *                  用ReentrantReadWriteLock读写合并锁，
 *                  读的时候rwlock.readLock().lock();rwlock.readLock().unlock();
 *                  写的时候rwlock.writeLock().lock();rwlock.writeLock().unlock();
 * @Author： cfl
 * @Date: Created in 17:14 2019/12/15
 * @Vesion 1.0
 */
public class DReadWriteLockDemo {
    public static void main(String[] args){
        MyCache myCache = new MyCache();
        for(int i = 1; i <= 5; i++){
            int finalI = i;
            new Thread(() ->{
                myCache.put(finalI+"",finalI);
            }, String.valueOf(i)).start();
        }
        for(int i = 1; i <= 5; i++){
            String finalI = i+"";
            new Thread(() ->{
                myCache.get(finalI);
            }, String.valueOf(i)).start();
        }
    }

}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
//    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put (String key, Object value){
        rwlock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"正在写入" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwlock.readLock().unlock();
        }
    }

    public void get (String key){
        rwlock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"正在读取" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完成" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwlock.writeLock().unlock();
        }
    }
}
