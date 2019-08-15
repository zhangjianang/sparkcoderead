package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static rabbitmq.MultiSend.DIRECT_EXCHANGE_NAME;
import static rabbitmq.MultiSend.EXCHANGE_NAME;
import static rabbitmq.Send.HOST;
import static rabbitmq.Send.PORT;

/**
 * Created by adimn on 2019/8/15.
 */
public class DirectRecv {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(DIRECT_EXCHANGE_NAME, "direct");

        String queueName = channel.queueDeclare().getQueue();

        if (args.length < 1) {
            System.err.println("Usage: ReceiveLogsDirect [warn] [error]");
            System.exit(1);
        }

        for (String severity : args) {
            channel.queueBind(queueName, DIRECT_EXCHANGE_NAME, severity);
        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" direct consum Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});

    }
}
