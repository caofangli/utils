package com.cfl.oneself.utils.learning.proxy;

import com.cfl.oneself.utils.learning.proxy.MyInvocationHandler;
import com.cfl.oneself.utils.learning.proxy.loginService;

import java.lang.reflect.Proxy;

/**
 * @ClassName： ProxyDemo
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 18:51 2019/12/8
 * @Vesion 1.0
 */
public class ProxyDemo {

    public static void main(String[] args){
        loginService o = (loginService)Proxy.newProxyInstance(loginService.class.getClassLoader(), new Class<?>[]{loginService.class}, new MyInvocationHandler());

        o.eat("三明治");
        String s = o.speak("张三");
        System.out.println(s);
        o.say();
        o.say("铁蛋儿");
    }
}
