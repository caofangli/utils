package com.cfl.oneself.utils.softAll;

import com.cfl.oneself.utils.entity.UserEntity;

/**
 * @ClassName： UserBiJiaoQi
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 17:11 2019/12/1
 * @Vesion 1.0
 */
public class UserBiJiaoQi implements BiJiaoQi<UserEntity> {
    @Override
    public boolean bijiao(UserEntity o1, UserEntity o2) {
        if(o1.getAge()>o2.getAge()){
            return true;
        }
        return false;
    }
}
