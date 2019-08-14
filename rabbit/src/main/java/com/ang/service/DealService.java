package com.ang.service;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

/**
 * Created by adimn on 2019/8/12.
 */
@Component
public class DealService extends Thread {
    @Value("${queue_name}")
    String queuename;

    @Value("${host}")
    String host;

    @Value("${port}")
    int port;

    private static int num = 0;


    @PostConstruct
    public void start(){
        super.start();
    }

    @Override
    public void run() {
        System.out.println("receive start ");
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setPort(port);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // 同一时刻服务器只会发一条消息给消费者
            channel.basicQos(1);

            channel.queueDeclareNoWait(queuename, true, false, false, null);
//                    channel.queueBind()

            System.out.println("waiting for message");


            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                num++;
                System.out.println("we received "+message);
                try {
                    if(num%2==0){

                        doWork(message);
                        System.out.println(" [x] Done");
//                    确认消息得到
//                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    }else {
                        throw new Exception();
                    }

                }catch (Exception e ){
                    // requeue the delivery
                    System.out.println("we reject "+message);
                    channel.basicReject(delivery.getEnvelope().getDeliveryTag(), true);
                }

            };
            // 监听队列，false表示手动返回完成状态，true表示自动
            channel.basicConsume(queuename, false, deliverCallback, consumerTag -> { });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doWork(String message){
        for (char ch : message.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }



    }

}
