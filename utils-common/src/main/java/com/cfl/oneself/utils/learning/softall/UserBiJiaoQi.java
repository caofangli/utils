package com.cfl.oneself.utils.learning.softall;

import com.cfl.oneself.utils.entity.BaseEntity;
import com.cfl.oneself.utils.learning.softall.BiJiaoQi;

/**
 * @ClassName： UserBiJiaoQi
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 17:11 2019/12/1
 * @Vesion 1.0
 */
public class UserBiJiaoQi implements BiJiaoQi<BaseEntity> {
    @Override
    public boolean bijiao(BaseEntity o1, BaseEntity o2) {
        if(o1.getAge()>o2.getAge()){
            return true;
        }
        return false;
    }
}
