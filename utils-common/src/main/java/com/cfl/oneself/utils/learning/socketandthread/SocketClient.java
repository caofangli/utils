package com.cfl.oneself.utils.learning.socketandthread;

import java.io.OutputStream;
import java.net.Socket;

/**
 * @ClassName： SocketClient
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 8:49 2019/12/9
 * @Vesion 1.0
 */
public class SocketClient {
    
    public static void main(String[] args) throws Exception {
        Socket sc = new Socket("127.0.0.1", 10000);
        OutputStream out = sc.getOutputStream();
        out.write("a".getBytes());
        out.close();
        sc.close();
    }
}
