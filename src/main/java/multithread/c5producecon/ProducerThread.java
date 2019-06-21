package multithread.c5producecon;

import java.util.Random;

/**
 * Created by adimn on 2019/6/21.
 */
public class ProducerThread extends Thread {
    private static  int i =0;
    private Table table;

    public ProducerThread(String name,Table table){
        super(name);
        this.table = table;
    }

    public void run(){
        try {
            while(i<1000){
                String prodce = "No."+i++;


                Thread.sleep(new Random().nextInt(100));

                table.put(prodce);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
