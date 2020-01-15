package com.cfl.oneself.utils.learning.inter.interviewthread2;

import com.cfl.oneself.utils.entity.BaseEntity;

/**
 * @ClassName： GTestTransferValue
 * @Description： 对象和引用
 * @Author： cfl
 * @Date: Created in 15:55 2019/12/15
 * @Vesion 1.0
 */
public class GTestTransferValue {

    public void changeValue1(int age){
        age = 30;
    }
    public void changeValue2(BaseEntity user) {
        user.setAge(11);
    }
    public void changeValue3(String str) {
        str = "xxx";
    }
    public static void main(String[] args){
        GTestTransferValue test = new GTestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println(age);

        BaseEntity user1 = BaseEntity.builder().id(1).userName("你是").password("123").age(11).build();
        test.changeValue2(user1);
        System.out.println(user1.getAge());

        String str = "abc";
        test.changeValue3(str);
        System.out.println(str);
    }
}
