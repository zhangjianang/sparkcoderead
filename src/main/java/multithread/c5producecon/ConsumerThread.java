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
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        new LazyThread("lazy",table).start();
        ConsumerThread consumerThread = new ConsumerThread("cons-01", table);
        consumerThread.start();

        ConsumerThread consumerThread2 = new ConsumerThread("cons-002",table);
        consumerThread2.start();

        new ConsumerThread("cons-003",table).start();
        new ConsumerThread("cons-004",table).start();

        ProducerThread producerThread = new ProducerThread("prod------1", table);
        producerThread.start();

        new ProducerThread("prod------2", table).start();
        new ProducerThread("prod------3", table).start();
        new ProducerThread("prod------4", table).start();



//        ClearThread clearThread = new ClearThread("clear***1",table);
//        clearThread.start();


    }
}
