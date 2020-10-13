package com.project.rabbitmqapi.quickstart;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName Producer
 * @date 2020/9/28 18:52
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        //获取connection
        Connection connection = RabbitConnection.createConnection();

        //通过connection创建channel
        Channel channel = connection.createChannel();

        String exchangeName = "test_direct_exchange";
        String routingKey = "test.direct";
        //通过channel发送数据
        String msg = "Hello,RabbitMQ!";
        channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());

        //关闭连接
        channel.close();
        connection.close();
    }
}
