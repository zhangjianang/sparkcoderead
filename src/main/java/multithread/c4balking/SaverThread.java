package multithread.c4balking;

import java.io.IOException;
import java.util.Random;

/**
 * Created by adimn on 2019/6/21.
 */
public class SaverThread extends Thread{

    private Data data;
    private Random random = new Random();

    public SaverThread(String name,Data data){
        super(name);
        this.data = data;
    }

    public void run(){
        try {
            for (int i = 0; i < 1000; i++) {
                data.change(" No." + i);

                Thread.sleep(random.nextInt(1000));

                data.save();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Data data = new Data("D://pic/sth.txt","default info");
        new ChangeThread("changer001",data).start();

        new SaverThread("saver-1",data).start();
//        new ChangeThread("changer002",data).start();
//        new SaverThread("saver-2",data).start();
    }
}
