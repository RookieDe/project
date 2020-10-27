package com.project.rabbitmqapi.quickstart.qos;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Product
 * @date 2020/10/27 14:01
 */
public class Product {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitConnection.createConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "test_qos_exchange";
        String routingKey = "qos.save";

        for (int i = 0; i < 20; i++) {
            String msg = i+"Hello RabbitMQ QOS Message";
            channel.basicPublish(exchangeName,routingKey,true,null,msg.getBytes());
        }
    }
}
