package com.project.rabbitmqapi.config;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Client
 * @date 2020/10/16 14:14
 */
public class Client {
    private static final int SERVER_PORT = 30000;
    private Socket socket = null;
    private BufferedReader reader = null;
    private Scanner scan = new Scanner(System.in);
    private PrintStream stream = null;

    public void init(){
        try {
            this.socket = new Socket("127.0.0.1", SERVER_PORT);
            this.stream = new PrintStream(this.socket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String tip = "";
            while(true){
                String username = JOptionPane.showInputDialog(tip+"输入用户名：");
                this.stream.println(CrazyitProtocol.USER_ROUND+username+CrazyitProtocol.USER_ROUND);
                String result = this.reader.readLine();
                if(CrazyitProtocol.NAME_REP.equals(result)){
                    tip = "用户名重复！请重新输入";
                    continue;
                }
                if(CrazyitProtocol.LOING_SUCESS.equals(result)){
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("找不到远程服务器，请确定服务器已启动！");
            this.closeStream();
            System.exit(1);
        } catch (IOException e) {
            System.out.println("网络异常！请重新登录！");
            this.closeStream();
            System.exit(1);
        }
        new ClientThread(this.reader).start();
    }
    private void closeStream(){
        try {
            if(this.scan!=null){
                this.scan.close();
            }
            if(this.reader!=null){
                this.reader.close();
            }
            if(this.stream!=null){
                this.stream.close();
            }
            if(this.socket!=null){
                this.socket.close();
            }
        } catch (Exception e) {
            System.out.println("客户端关闭流出现异常！");
        }
    }
    private void readAndSend(){
        try {
            String line = null;
            while((line=this.scan.nextLine())!=null){
                if(line.indexOf(":")>0 && line.startsWith("//")){
                    line = line.substring(2);
                    this.stream.println(CrazyitProtocol.PRIVATE_ROUND+line.split(":")[0]+CrazyitProtocol.SPLIT_SING+line.split(":")[1]+CrazyitProtocol.PRIVATE_ROUND);
                }else{
                    this.stream.println(CrazyitProtocol.MSG_ROUND+line+CrazyitProtocol.MSG_ROUND);
                }
            }
        } catch (Exception e) {
            System.out.println("网络通信异常，请重新登录");
            this.closeStream();
        }
    }
    public static void main(String[] args) {
        Client client = new Client();
        client.init();
        client.readAndSend();
    }
}

