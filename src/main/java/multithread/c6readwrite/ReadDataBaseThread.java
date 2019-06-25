package multithread.c6readwrite;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by adimn on 2019/6/25.
 */
public class ReadDataBaseThread extends Thread {

    private DataBase<String,String> dataBase;
    private String key;
    private static AtomicInteger num = new AtomicInteger(0);

    public ReadDataBaseThread(DataBase<String,String> dataBase,String key){
        this.dataBase = dataBase;
        this.key = key;
    }

    @Override
    public void run() {
        while (true){
            int i = num.incrementAndGet();
            String res = dataBase.get(key);

            System.out.println(i+"--"+Thread.currentThread().getName()+" get "+ res);
        }
    }

    public static void main(String[] args) {
        DataBase<String,String> db = new DataBase();
        for(int i=0;i<100;i++){
            new WriteDatabaseThread(db,"lili","love").start();
            new ReadDataBaseThread(db,"lili").start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
