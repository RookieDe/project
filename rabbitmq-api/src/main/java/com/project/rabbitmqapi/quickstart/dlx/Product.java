package com.project.rabbitmqapi.quickstart.dlx;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.AMQP;
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

        String exchangeName = "test_dlx_exchange";
        String routingKey = "dlx.save";

        /**
         * dlx
         * 死信队列：针对消息被拒绝，或者TTL过期，或者消息达到最大长度，放入到死信队列，做一个补偿机制
         */
        for (int i = 0; i < 1; i++) {
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .expiration("10000")
                    .build();
            String msg = "Hello RabbitMQ DLX Message";
            channel.basicPublish(exchangeName,routingKey,true,basicProperties,msg.getBytes());
        }
    }
}
