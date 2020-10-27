package com.project.rabbitmqapi.config;

import java.io.BufferedReader;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName ClientThread
 * @date 2020/10/16 14:15
 */
public class ClientThread extends Thread {
    private BufferedReader reader = null;
    public ClientThread(BufferedReader reader){
        this.reader = reader;
    }
    @Override
    public void run() {
        try {
            String line = null;
            while((line=this.reader.readLine())!=null){
                System.out.println(line+"--------");
            }
        } catch (Exception e) {
            System.out.println("读取服务端内容时出现异常！");
        } finally {
            try {
                if(this.reader!=null){
                    this.reader.close();
                }
            } catch (Exception e2) {
                System.out.println("关闭客户端ClientThread中读入流时出现异常！");
            }
        }
    }
}


