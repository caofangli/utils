package com.cfl.oneself.utils.socktrpc;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @ClassName： ReadDataUtil
 * @Description： 创建动态代理逻辑，通过socket（socketServer）以及接口只定义方法就调用另一个服务器中的方法的具体实现逻辑
 * @Author： cfl
 * @Date: Created in 9:06 2019/12/9
 * @Vesion 1.0
 */
public class ReadDataUtil {

    public ReadData getInstance(){
        Proxy.newProxyInstance(ReadData.class.getClassLoader(), new Class[]{ReadData.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String name = method.getName();
                        if(name.equals("findProductById")){
                            Socket sc = new Socket("127.0.0.1", 10000);
                            OutputStream out = sc.getOutputStream();
                            InputStream in = sc.getInputStream();
                            out.write(("select * from p.dat where id=" + args[0]).getBytes());
                            ObjectInputStream ois = new ObjectInputStream(in);
                            Product p = (Product)ois.readObject();
                            ois.close();
                            in.close();
                            out.close();
                            sc.close();

                            return p;
                        } else {
                            Socket sc = new Socket("127.0.0.1", 10000);
                            OutputStream out = sc.getOutputStream();
                            InputStream in = sc.getInputStream();
                            out.write(("select * from p.dat where name like " + args[0]).getBytes());
                            ObjectInputStream ois = new ObjectInputStream(in);
                            Product p = (Product)ois.readObject();
                            ois.close();
                            in.close();
                            out.close();
                            sc.close();

                            return p;
                        }
                    }
                });
        return null;
    }
}
