package com.project.rabbitmqapi.quickstart.ack;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        String exchangeName = "test_ack_exchange";
        String routingKey = "ack.save";

        for (int i = 0; i < 20; i++) {
            Map<String,Object> headers = new HashMap<>(64);
            headers.put("num",i);
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .headers(headers)
                    .build();
            String msg = i+"Hello RabbitMQ QOS Message";
            channel.basicPublish(exchangeName,routingKey,true,basicProperties,msg.getBytes());
        }
    }
}
