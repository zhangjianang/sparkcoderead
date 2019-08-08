package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by adimn on 2019/8/8.
 */
public class Send {
    public static final String QUEUE_NAME = "helloAng";
    public static final String HOST = "***";
    public static final int PORT = 000;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String sendMsg = "you will know that i am on ";

        channel.basicPublish("",QUEUE_NAME,null,sendMsg.getBytes());

        System.out.println("we send it again");

        channel.close();
        connection.close();

    }
}
