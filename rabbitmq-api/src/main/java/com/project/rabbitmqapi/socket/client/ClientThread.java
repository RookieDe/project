package com.project.rabbitmqapi.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName ClientThread
 * @date 2020/10/15 16:52
 */

public class ClientThread implements Runnable {
    private Socket socket = null;
    private BufferedReader reader = null;
    public ClientThread(Socket socket){
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            String content = null;
            while((content=this.reader.readLine())!=null){
                System.out.println("服务器："+content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

