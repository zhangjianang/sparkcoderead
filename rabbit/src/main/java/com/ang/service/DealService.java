package com.ang.service;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

/**
 * Created by adimn on 2019/8/12.
 */
@Component
public class DealService {
    @Value("${queue_name}")
    String queuename;

    @Async
    @PostConstruct
    public void doTaskOne() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclareNoWait(queuename, true, false, false, null);

        System.out.println("waiting for message");

        DefaultConsumer consumer = new DefaultConsumer(channel) {

            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String message = new String(body,"UTF-8");
                System.out.println("we received"+ message);
            }
        };

        channel.basicConsume(queuename,true,consumer);
    }

}
