package com.project.rabbitmqapi.quickstart.confirm;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Consumer
 * @date 2020/10/23 18:20
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        //获取connection
        Connection connection = RabbitConnection.createConnection();
        //通过connection创建channel
        Channel channel = connection.createChannel();

        //声明一个队列
        String exchangeName = "test_confirm_exchange";
        String routingkey = "confirm.save";
        String queueName = "test_confirm_queue";

        //声明交换机和队列，然后进行绑定，最后指定路由key
        channel.exchangeDeclare(exchangeName,"topic",true);
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,routingkey);

        //消费消息，推模式
        channel.basicQos(100);

        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                StringBuilder sb = new StringBuilder();
                for (byte b : body) {
                    sb.append((char) b);
                }
                System.err.println(sb.toString());
            }
        });

    }
}
