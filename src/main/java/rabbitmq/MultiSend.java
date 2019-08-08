package rabbitmq;

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
    public static final String QUEUE_NAME_ALL= "helloAagain";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(PORT);
        factory.setHost(HOST);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME_ALL,true,false,false,null);

        for(int i =3;i<6;i++) {
            String message = "hello everyone "+ i;

            channel.basicPublish("", QUEUE_NAME_ALL,
                    PERSISTENT_TEXT_PLAIN,
                    message.getBytes("utf-8"));

            System.out.println("we send " + message);
        }
        channel.close();
        connection.close();
    }
}
