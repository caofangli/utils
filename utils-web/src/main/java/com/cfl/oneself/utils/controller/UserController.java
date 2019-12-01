package com.cfl.oneself.utils.controller;

import com.cfl.oneself.utils.entity.UserEntity;
import com.cfl.oneself.utils.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName： UserController
 * @Description： 用户登陆
 * @Author： cfl
 * @Date: Created in 19:36 2019/11/30
 * @Vesion 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public UserEntity login(@RequestBody UserEntity user) {
        System.out.println("1111");
        return service.login(user);
    }

}
