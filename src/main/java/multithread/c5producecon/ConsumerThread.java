package multithread.c5producecon;

import java.util.Random;

/**
 * Created by adimn on 2019/6/21.
 */
public class ConsumerThread extends Thread {
    private Table table;

    public ConsumerThread(String name,Table table){
        super(name);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true){
                table.take();

                Thread.sleep(new Random().nextInt(1000));


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Table table = new Table(3);
        new ConsumerThread("cons-01",table).start();
        new ConsumerThread("cons-002",table).start();
        new ProducerThread("prod------1",table).start();
    }
}
