package multithread.c8perworker;

import java.util.Random;

/**
 * Created by adimn on 2019/6/28.
 */
public class ClientThread extends Thread{
    private Channel channel;
    private Random random = new Random();

    public ClientThread(String name,Channel channel){
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        for(int i= 0;i<10;i++){
            Request request  =  new Request(getName(),i);
            channel.addRequest(request);
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
