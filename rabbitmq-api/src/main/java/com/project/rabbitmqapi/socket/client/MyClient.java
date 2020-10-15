package com.project.rabbitmqapi.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName MyClient
 * @date 2020/10/15 16:51
 */
public class MyClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 30000);
        new Thread(new ClientThread(socket)).start();
        PrintStream stream = new PrintStream(socket.getOutputStream());
        String line = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while((line=reader.readLine())!=null){
            stream.println(line);
        }
    }
}
