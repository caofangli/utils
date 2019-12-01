package com.cfl.oneself.utils.soft;

import com.cfl.oneself.utils.entity.UserEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName： SoftUtil
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 15:42 2019/12/1
 * @Vesion 1.0
 */
public class softUtil {
    
    public static void main(String[] args){

        List users = Arrays.asList(
            UserEntity.builder().id(1).userName("你是").password("123").age(11).build(),
            UserEntity.builder().id(2).userName("小明").password("123456").age(19).build(),
            UserEntity.builder().id(3).userName("小李").password("456").age(18).build(),
            UserEntity.builder().id(4).userName("小王").password("12343").age(26).build(),
            UserEntity.builder().id(5).userName("小赵").password("789").age(22).build(),
            UserEntity.builder().id(6).userName("小曹").password("0123").age(15).build()
        );
//        List soft = soft(users);
//        List soft = soft(users, new AgeBiJiaoQi());
        List soft = soft(users, new IdBiJiaoQi());
        soft.stream().forEach(user -> {
            System.out.println(user.toString());
        });

    }

    public static List<UserEntity> soft(List<UserEntity> users, BiJiaoQi bijiaoqi){
        for(int i =0;i<users.size()-1;i++){
            for(int j=0;j<users.size()-1-i;j++){
                boolean daxiao = bijiaoqi.bijiao(users.get(j), users.get(j+1));
                if(daxiao){
                    UserEntity tmp = users.get(j);
                    users.set(j,users.get(j+1));
                    users.set(j+1,tmp);
                }
            }
        }
        return users;
    }
}
