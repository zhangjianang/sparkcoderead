package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import static rabbitmq.MultiSend.QUEUE_NAME_ALL;
import static rabbitmq.Send.HOST;
import static rabbitmq.Send.PORT;

/**
 * Created by adimn on 2019/8/8.
 */
public class MultiRecv {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(HOST);
        factory.setPort(PORT);

        Connection connection = factory.newConnection();

        final Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME_ALL, true, false, false, null);

        System.out.println("waiting to receive");

        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {

            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String message = new String(body, "utf-8");
                System.out.println("received "+ message);

                try {
                    Thread.sleep(10000);
                    channel.basicAck(envelope.getDeliveryTag(),false);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        channel.basicConsume(QUEUE_NAME_ALL,consumer);

    }
}
