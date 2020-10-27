package com.project.rabbitmqapi.quickstart.confirm;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Producer
 * @date 2020/10/23 18:20
 */
public class Producer {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitConnection.createConnection();
        //通过connection创建也给新的channel
        Channel channel = connection.createChannel();

        //指定我们的消息投递模式：消息的确认模式
        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routingkey = "confirm.save";

        String msg = "Hello,RabbitMQ; Send confirm message!";
        channel.basicPublish(exchangeName,routingkey,null,msg.getBytes());

        //添加一个回调确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("----ack!--------");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("----no ack!--------");
            }
        });

    }
}
