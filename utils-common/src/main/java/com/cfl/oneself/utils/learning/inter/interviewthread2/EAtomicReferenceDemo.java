package com.cfl.oneself.utils.learning.inter.interviewthread2;

import com.cfl.oneself.utils.entity.BaseEntity;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName： EAtomicReferenceDemo
 * @Description： ABA问题-由来演示
 * 解决：加入版本号的字段，乐观锁的做法
 * @Author： cfl
 * @Date: Created in 14:30 2019/12/15
 * @Vesion 1.0
 */
public class EAtomicReferenceDemo {
    public static void main(String[] args) {
        BaseEntity user1 = BaseEntity.builder().id(1).userName("你是").password("123").age(11).build();
        BaseEntity user2 = BaseEntity.builder().id(1).userName("你是").password("123").age(15).build();
        AtomicReference<BaseEntity> userEntityAtomicReference = new AtomicReference<>();
        userEntityAtomicReference.set(user1);
        System.out.println(userEntityAtomicReference.compareAndSet(user1,user2) + "\t" + userEntityAtomicReference.get().toString());
    }
}
