package com.example.demo.controller.socket;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName MyServer
 * @date 2020/10/16 14:12
 */
public class MyServer {
    private static final int SERVER_PORT = 30000;
    public static CrazyitMap<String, PrintStream> clients = new CrazyitMap<>();
    public void init(){
        try {
            ServerSocket server = new ServerSocket(SERVER_PORT);
            while(true){
                Socket socket = server.accept();
                new ServerThread(socket).start();
            }
        } catch (Exception e) {
            System.out.println("服务器启动失败，是否端口"+SERVER_PORT+"已经被占用？");
        }
    }
    public static void main(String[] args) {
        MyServer server = new MyServer();
        server.init();
    }
}

