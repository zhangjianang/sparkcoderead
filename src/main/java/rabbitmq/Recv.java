package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import static rabbitmq.Send.HOST;
import static rabbitmq.Send.PORT;
import static rabbitmq.Send.QUEUE_NAME;

/**
 * Created by adimn on 2019/8/8.
 */
public class Recv {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclareNoWait(QUEUE_NAME, false, false, false, null);

        System.out.println("waiting for message");

        DefaultConsumer consumer = new DefaultConsumer(channel) {

            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String message = new String(body,"UTF-8");
                System.out.println("we received"+ message);
            }
        };

        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
