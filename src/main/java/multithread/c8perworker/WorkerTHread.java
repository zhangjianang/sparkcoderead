package multithread.c8perworker;

/**
 * Created by adimn on 2019/6/28.
 */
public class WorkerTHread extends Thread {
    private Channel channel;

    public WorkerTHread (String name,Channel channel){
        super(name);
        this.channel = channel;
    }

    public void run(){
        while(true){
            Request request = channel.ExecuteRequest();
            request.execute();
        }
    }

    public static void main(String[] args) {
        Channel channel = new Channel(10,5);
        channel.startWorker();
        new ClientThread("ang",channel).start();
        new ClientThread("lili",channel).start();
        new ClientThread("john",channel).start();

//        new WorkerTHread("worker1",channel).start();
//        new WorkerTHread("worker2",channel).start();
//        new WorkerTHread("worker3",channel).start();
//        new WorkerTHread("worker4",channel).start();
    }
}
