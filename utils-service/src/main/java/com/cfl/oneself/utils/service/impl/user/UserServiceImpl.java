package com.cfl.oneself.utils.service.impl.user;

import com.cfl.oneself.utils.dao.user.UserDao;
import com.cfl.oneself.utils.entity.user.UserEntity;
import com.cfl.oneself.utils.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName： UserServiceImpl
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 20:15 2019/11/30
 * @Vesion 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public UserEntity login(UserEntity user) {
        return dao.login(user);
    }
}
