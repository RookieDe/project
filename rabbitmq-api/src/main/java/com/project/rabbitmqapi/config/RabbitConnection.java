package com.project.rabbitmqapi.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName RabbitConnection
 * @date 2020/9/29 9:57
 */
public class RabbitConnection {

    private final static String HOST = "47.116.78.122";

    private final static String VIRTUALHOST = "/";

    private final static int PORT = 5672;

    private final static String USERNAME = "admin";

    private final static String PASSWORD = "123456";


    /**
     * 获取connection
     * */
    public static Connection createConnection() {
        Connection conn = null;
        //1.创建一个ConnectionFactory,并进行配置
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setVirtualHost(VIRTUALHOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);

        try {
            conn = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return conn;
    }


}
