package com.cfl.oneself.utils.learning.proxy;

import com.cfl.oneself.utils.learning.proxy.loginServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName： MyInvocationHandler
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 19:01 2019/12/8
 * @Vesion 1.0
 */
public class MyInvocationHandler implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("eat")){
            System.out.println("我一点都不喜欢吃" + args[0]);
        }
        if(method.getName().equals("speak")){
            System.out.println("speak方法暂无动态代理逻辑");
            return new loginServiceImpl().speak(args[0].toString());
        }
        if(method.getName().equals("say")){
            if(method.getParameterTypes().length>0){
                System.out.println("我爱死" + args[0] + "了");
            }else{
                System.out.println("调用了无参数的say方法");
            }
        }
        return null;
    }
}
