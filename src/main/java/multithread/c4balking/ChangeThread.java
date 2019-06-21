package multithread.c4balking;

import org.apache.spark.sql.catalyst.expressions.Rand;

import java.io.IOException;
import java.util.Random;

/**
 * Created by adimn on 2019/6/21.
 */
public class ChangeThread extends Thread {

    private Data data;

    public ChangeThread(String name,Data data){
        super(name);
        this.data = data;
    }

    public void run(){
        while (true){
            try {
                data.save();

                Thread.sleep(new Random().nextInt(1000));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
