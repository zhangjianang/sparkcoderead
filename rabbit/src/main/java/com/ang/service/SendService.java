package com.ang.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by adimn on 2019/8/8.
 */
@Component
@PropertySource(value = {"classpath:rabbit.properties"})
public class SendService {

    @Value("${queue_name}")
    public String queuename;
    @Value("${host}")
    private String host;
    @Value("${port}")
    private int port;


    private ConnectionFactory factory = new ConnectionFactory();


    public void addInfo(String message) throws IOException, TimeoutException {

        factory.setHost(host);
        factory.setPort(port);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(queuename, true, false, false, null);


        channel.basicPublish("", queuename, null, message.getBytes());

        System.out.println("we send : " + message);

//        channel.close();
//        connection.close();
    }
}
