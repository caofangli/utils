package com.cfl.oneself.utils.service.impl;

import com.cfl.oneself.utils.dao.BaseDao;
import com.cfl.oneself.utils.entity.BaseEntity;
import com.cfl.oneself.utils.service.BaseService;
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
public class BaseServiceImpl implements BaseService {

    @Autowired
    private BaseDao dao;

    @Override
    public BaseEntity login(BaseEntity user) {
        return dao.login(user);
    }
}
