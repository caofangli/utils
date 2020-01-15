package com.cfl.oneself.utils.learning.soft;

import com.cfl.oneself.utils.entity.user.UserEntity;

/**
 * @ClassName： IdBiJiaoQi
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 16:34 2019/12/1
 * @Vesion 1.0
 */
public class IdBiJiaoQi implements BiJiaoQi {
    @Override
    public boolean bijiao(UserEntity u1, UserEntity u2) {
        if(u1.getId()<u2.getId()){
            return true;
        }
        return false;
    }
}
