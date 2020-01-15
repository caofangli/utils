package com.cfl.oneself.utils.learning.proxy;

import com.cfl.oneself.utils.learning.proxy.loginService;

/**
 * @ClassName： loginServiceImpl
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 18:49 2019/12/8
 * @Vesion 1.0
 */
public class loginServiceImpl implements loginService {
    @Override
    public void say() {
        System.out.println("你好");
    }

    @Override
    public void say(String name) {
        System.out.println("你好"+name);
    }

    @Override
    public void eat(String food) {
        System.out.println("吃饭了，吃的是："+food);
    }

    @Override
    public String speak(String name) {
        return "hello" + name;
    }
}
