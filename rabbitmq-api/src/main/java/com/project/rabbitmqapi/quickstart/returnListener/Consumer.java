package com.project.rabbitmqapi.quickstart.returnListener;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Consumer
 * @date 2020/10/27 14:02
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitConnection.createConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "test_return_exchange";
        String routingKey = "return.#";
        String queueName = "test_return_queue";

        channel.exchangeDeclare(exchangeName,"topic",true,false,null);
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,exchangeName,routingKey);

        //消费消息，推模式
        channel.basicQos(100);
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                StringBuilder stringBuilder= new StringBuilder();
                for (byte b : body) {
                    stringBuilder.append((char)b);
                }
                System.err.println(stringBuilder.toString());
            }
        });

    }

}
