package com.project.rabbitmqapi.quickstart.ack;

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

        String exchangeName = "test_ack_exchange";
        String routingKey = "ack.#";
        String queueName = "test_ack_queue";

        channel.exchangeDeclare(exchangeName,"topic",true,false,null);
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,exchangeName,routingKey);


        channel.basicConsume(queueName,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                StringBuilder stringBuilder= new StringBuilder();
                for (byte b : body) {
                    stringBuilder.append((char)b);
                }
                System.err.println(stringBuilder.toString());
                if ((Integer) properties.getHeaders().get("num") == 0){
                    //requeue是否放回到消息队列中去
                    channel.basicNack(envelope.getDeliveryTag(),true,true);
                }else {
                    channel.basicAck(envelope.getDeliveryTag(),true);
                }
            }
        });

    }

}
