package com.project.rabbitmqapi.quickstart.returnListener;

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

        String exchangeName = "test_return_exchange";
        String routingKey = "return.save";
        String routingKeyError = "abc.save";

        String msg = "Hello RabbitMQ Return Message";

        channel.addReturnListener((replyCode, replyText, exchange1, routingKey1, properties, body) -> {

            System.err.println("----------- return Listener ----------");
            System.err.println("replyCode: " + replyCode);
            System.err.println("replyText: " + replyText);
            System.err.println("exchange: " + exchange1);
            System.err.println("routingKey: " + routingKey1);
            System.err.println("properties: " + properties);
            System.err.println("body: " + new String(body));
        });

        channel.basicPublish(exchangeName,routingKeyError,true,null,msg.getBytes());


    }
}
