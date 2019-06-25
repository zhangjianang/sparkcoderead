package multithread.c6readwrite;

import java.util.Random;

/**
 * Created by adimn on 2019/6/25.
 */
public class WriteDatabaseThread extends Thread {
    private String key;
    private String value;
    private DataBase dataBase;

    public WriteDatabaseThread(DataBase<String,String> database,String key,String value){
        this.dataBase = database;
        this.key = key;
        this.value = value;
    }

    @Override
    public void run() {
        while(true){
            dataBase.put(key,value);
            System.out.println(Thread.currentThread().getName()+" puts "+ key+"--"+value);
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
