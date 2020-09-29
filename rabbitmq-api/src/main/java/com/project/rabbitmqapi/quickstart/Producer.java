package com.project.rabbitmqapi.quickstart;

import com.project.rabbitmqapi.config.RabbitConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Producer
 * @date 2020/9/28 18:52
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        //获取connection
        Connection connection = RabbitConnection.createConnection();

        //通过connection创建channel
        Channel channel = connection.createChannel();

        //通过channel发送数据
        String msg = "Hello,RabbitMQ!";
        channel.basicPublish("","test001",null,msg.getBytes());

        //关闭连接
        channel.close();
        connection.close();
    }
}
