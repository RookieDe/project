package com.project.rabbitmqapi.quickstart.dlx;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange","dlx.exchange");

        String exchangeName = "test_dlx_exchange";
        String routingKey = "dlx.#";
        String queueName = "test_dlx_queue";

        //这是一个普通的队列
        channel.exchangeDeclare(exchangeName,"topic",true,false,null);
        //这个arguments属性，要设置到声明队列上
        channel.queueDeclare(queueName,true,false,false,map);
        channel.queueBind(queueName,exchangeName,routingKey);

        //要进行死信队列声明
        channel.exchangeDeclare("dlx.exchange","topic",true,false,null);
        channel.queueDeclare("dlx.queue",true,false,false,null);
        channel.queueBind("dlx.queue","dlx.exchange","#");


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
