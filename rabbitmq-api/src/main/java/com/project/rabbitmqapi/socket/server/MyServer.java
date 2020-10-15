package com.project.rabbitmqapi.socket.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName MyServer
 * @date 2020/10/15 16:48
 */
public class MyServer {

    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(30000);
        while(true){
            Socket socket = server.accept();
            new Thread(new ServerThread(socket)).start();
            String line = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintStream stream = new PrintStream(socket.getOutputStream());
            while((line=reader.readLine())!=null){
                stream.println(line);
            }
        }
    }

}
