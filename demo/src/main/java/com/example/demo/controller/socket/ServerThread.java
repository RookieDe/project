package com.example.demo.controller.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName ServerThread
 * @date 2020/10/15 16:49
 */
public class ServerThread extends Thread {
    private Socket socket = null;
    private BufferedReader reader = null;
    private PrintStream stream = null;
    public ServerThread(Socket socket){
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            stream = new PrintStream(this.socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("服务器端创建对应读入、输出流出现异常！");
        }
    }
    @Override
    public void run() {
        try {
            String line = null;
            while((line=reader.readLine())!=null){
                if(line.startsWith(CrazyitProtocol.USER_ROUND) && line.endsWith(CrazyitProtocol.USER_ROUND)){
                    //得到真实消息
                    String userName = getRealMsg(line);
                    if(MyServer.clients.map.containsKey(userName)){
                        System.out.println("用户名重复");
                        stream.println(CrazyitProtocol.NAME_REP);
                    }else{
                        System.out.println("登录成功");
                        this.stream.println(CrazyitProtocol.LOING_SUCESS);
                        MyServer.clients.map.put(userName, stream);
                    }
                }else if(line.startsWith(CrazyitProtocol.PRIVATE_ROUND) && line.endsWith(CrazyitProtocol.PRIVATE_ROUND)){
                    String userAndMsg = getRealMsg(line);
                    String user = userAndMsg.split(CrazyitProtocol.SPLIT_SING)[0];
                    String msg = userAndMsg.split(CrazyitProtocol.SPLIT_SING)[1];
                    MyServer.clients.map.get(user).println(MyServer.clients.getKeyByValue(stream)+"悄悄对你说："+msg);
                }else{
                    String msg = getRealMsg(line);
                    for(PrintStream clientPs : MyServer.clients.valueSet()){
                        clientPs.println(MyServer.clients.getKeyByValue(stream)+"说："+msg);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("客户端"+MyServer.clients.getKeyByValue(this.stream)+"已经关闭！");
            MyServer.clients.removeByValue(this.stream);
            System.out.println(MyServer.clients.map.size());
            try {
                if(this.reader!=null){
                    this.reader.close();
                }
                if(this.stream!=null){
                    this.stream.close();
                }
                if(this.socket!=null){
                    this.socket.close();
                }
            } catch (Exception e2) {
                System.out.println("客户端已经关闭，关闭与之对应的服务端的线程中socket、输出、输入流出现异常！");
            }
        }

    }
    private String getRealMsg(String line){
        return line.substring(CrazyitProtocol.PROTOCOL_LEN,line.length()-CrazyitProtocol.PROTOCOL_LEN);
    }
}


