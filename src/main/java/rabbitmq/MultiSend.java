package rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.rabbitmq.client.MessageProperties.PERSISTENT_TEXT_PLAIN;
import static rabbitmq.Send.HOST;
import static rabbitmq.Send.PORT;


/**
 * Created by adimn on 2019/8/8.
 */

public class MultiSend {

    public static final String QUEUE_NAME_ALL = "helloAagain";

    public static final String EXCHANGE_NAME = "logs";
    public static final String DIRECT_EXCHANGE_NAME = "d_logs";
    public static final String TOPIC_EXCHANGE_NAME = "itopic";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(PORT);
        factory.setHost(HOST);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

//        work with multiRecv
//        basicWorkerQueue(channel);

        // work with fanoutRecv
//        exchangeQueue(channel);

//        work with DirectRecv
//        directExchangeQueue(channel);

//        work with  TopicRecv
        topicExchangeQueue(channel,args);


        channel.close();
        connection.close();
    }


    public static void basicWorkerQueue(Channel channel) throws IOException, InterruptedException {

        channel.queueDeclare(QUEUE_NAME_ALL, true, false, false, null);

        for (int i = 3; i < 10; i++) {
            String message = "hello everyone " + i;

//            using a default exchange, ""
//            have persistent
            channel.basicPublish("", QUEUE_NAME_ALL,
                    PERSISTENT_TEXT_PLAIN,
                    message.getBytes("utf-8"));

            System.out.println("we send " + message);
            Thread.sleep(1000);
        }
    }

    public static void exchangeQueue(Channel channel) throws IOException {

        for (int i = 3; i < 10; i++) {
            String message = "fanout everyone " + i;
//           声明为交换模式
            System.out.println("we send " + message);
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        }
    }


    public static void directExchangeQueue(Channel channel) throws IOException {
        for (int i = 0; i < 10; i++) {
            String message = "direct hi " + i;

            channel.exchangeDeclare(DIRECT_EXCHANGE_NAME, "direct");

            String severity;
            if (i % 3 == 0) {
                severity = "warn";
            } else if (i % 3 == 1) {
                severity = "info";
            }else {
                severity = "error";
            }

            channel.basicPublish(DIRECT_EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" direct exchange Sent '" + severity + "':'" + message + "'");

        }
    }


    public static void topicExchangeQueue(Channel channel,String[] argv) throws IOException {

//        * (star) can substitute for exactly one word.
//        # (hash) can substitute for zero or more words.
//        "kern.*" "we are here"
//        "ang.critical" "we are lazy"  对应consumer ang.* 或者#.critical
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic");
        String routingKey = argv[0];
        String message = argv[1];
        channel.basicPublish(TOPIC_EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
        System.out.println(" topic Sent '" + routingKey + "':'" + message + "'");
    }



}
