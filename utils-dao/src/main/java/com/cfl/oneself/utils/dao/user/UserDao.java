package com.cfl.oneself.utils.dao.user;

import com.cfl.oneself.utils.entity.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： UserDao
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 20:22 2019/11/30
 * @Vesion 1.0
 */
@Mapper
public interface UserDao {

    UserEntity login(UserEntity user);

}
