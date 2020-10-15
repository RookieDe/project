package com.project.rabbitmqapi.socket.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName ServerThread
 * @date 2020/10/15 16:49
 */
public class ServerThread implements Runnable {

    private Socket socket = null;
    private BufferedReader reader = null;
    public ServerThread(Socket socket){
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        try {
            String content = null;
            while((content=this.readFromClient())!=null){
                System.err.println("客户端："+content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFromClient(){
        try {
            return this.reader.readLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
        return null;
    }

}

