package com.cfl.oneself.utils.learning.socketandthread;

import com.cfl.oneself.utils.learning.socketandthread.SocketThread;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName： SocketServer
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 12:25 2019/12/8
 * @Vesion 1.0
 */
public class SocketServer {

    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(10000);
        while(true){
            Socket sc = ss.accept();//建立socket连接，会一直等待。
            new Thread(new SocketThread(sc)).start();

        }
    }
}
