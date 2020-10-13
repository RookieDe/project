package com.project.rabbitmqapi.quickstart;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName consumer
 * @date 2020/9/28 18:43
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        //获取connection
        Connection connection = RabbitConnection.createConnection();

        //通过connection创建channel
        Channel channel = connection.createChannel();

        //声明（创建）一个队列

        String exchangeName = "test_direct_exchange";
        String exchangeType = "direct";
        String queueName = "test_direct_queue";
        String routingKey = "test.direct";

        //表示声明了一个交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        //表示声明了一个队列
        channel.queueDeclare(queueName,false,false,false,null);
        //建立一个绑定关系
        channel.queueBind(queueName,exchangeName,routingKey);

        //消费消息，推模式
        channel.basicQos(100);
        //设置channel，并创建消费者
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            //获取消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                /*System.out.println(consumerTag);
                System.out.println(envelope.getDeliveryTag());
                System.out.println(envelope.getExchange());
                System.out.println(envelope.getRoutingKey());*/
                StringBuilder sb = new StringBuilder();
                for (byte b : body) {
                    sb.append((char) b);
                }
                System.err.println(sb.toString());
                //channel.basicAck(envelope.getDeliveryTag(), false);//这个可以确认是否处理消息
            }
        });

    }
}
