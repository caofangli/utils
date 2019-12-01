package com.cfl.oneself.utils.soft;

import com.cfl.oneself.utils.entity.UserEntity;

/**
 * @ClassName： AgeBiJiaoQi
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 16:32 2019/12/1
 * @Vesion 1.0
 */
public class AgeBiJiaoQi implements BiJiaoQi {
    @Override
    public boolean bijiao(UserEntity u1, UserEntity u2) {
        if(u1.getAge()>u2.getAge()){
            return true;
        }
        return false;
    }
}
