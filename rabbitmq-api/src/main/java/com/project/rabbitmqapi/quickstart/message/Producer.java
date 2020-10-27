package com.project.rabbitmqapi.quickstart.message;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

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
        Map<String,Object> headers = new HashMap<>();
        headers.put("head1","这是自定义请求头1");
        headers.put("head2","这是我创建的自定义2");

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2)
                .contentEncoding("UTF-8")
                .expiration("10000")
                .headers(headers)
                .build();

        channel.basicPublish(exchangeName,routingKey,basicProperties,msg.getBytes());

        //关闭连接
        channel.close();
        connection.close();
    }
}
