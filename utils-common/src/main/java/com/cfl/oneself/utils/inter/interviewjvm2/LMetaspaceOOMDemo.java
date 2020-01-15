package com.cfl.oneself.utils.inter.interviewjvm2;

import com.cfl.oneself.utils.entity.UserEntity;

import java.util.Arrays;
import java.util.List;

/**
 * java.lang.OutOfMemoryError:Metaspace
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * 元空间被撑爆了，课程：java大厂面试87
 */
public class LMetaspaceOOMDemo {
    public static void main(String[] args){

        try{
            for (int i = 0;   ;i++) {
                List users = Arrays.asList(
                        UserEntity.builder().id(1).userName("你是").password("123").age(11).build(),
                        UserEntity.builder().id(2).userName("小明").password("123456").age(19).build(),
                        UserEntity.builder().id(3).userName("小李").password("456").age(18).build(),
                        UserEntity.builder().id(4).userName("小王").password("12343").age(26).build(),
                        UserEntity.builder().id(5).userName("小赵").password("789").age(22).build(),
                        UserEntity.builder().id(6).userName("小曹").password("0123").age(15).build()
                );
                System.out.println(users);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
