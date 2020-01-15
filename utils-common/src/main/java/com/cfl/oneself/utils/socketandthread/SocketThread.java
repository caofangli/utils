package com.cfl.oneself.utils.socketandthread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @ClassName： SocketThread
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 12:29 2019/12/8
 * @Vesion 1.0
 */
public class SocketThread implements Runnable {

    Socket sc;

    public SocketThread(Socket sc){
        this.sc=sc;
    }

    @Override
    public void run() {
        try {
            InputStream is = sc.getInputStream();
            OutputStream os = sc.getOutputStream();

            byte[] b = new byte[1024];
            int num = is.read(b);
            System.out.println("收到客户端的问题：" + new String(b,0,num));

            // 回复客户端问题
            os.write("我是谁谁谁".getBytes());
            
            num = is.read();
            System.out.println("收到客户端的问题2:" + new String(b,0,num));

            os.write("我多少多少岁".getBytes());

            os.close();
            is.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
