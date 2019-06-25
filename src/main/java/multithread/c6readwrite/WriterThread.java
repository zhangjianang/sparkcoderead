package multithread.c6readwrite;

import java.util.Random;

/**
 * Created by adimn on 2019/6/25.
 */
public class WriterThread extends Thread {
    private Data data;
    private String filler ;

    public WriterThread(String name,Data data,String filler){
        super(name);
        this.data = data;
        this.filler = filler;

    }

    @Override
    public void run() {
        while (true){
            for(int i=0;i<filler.length();i++){
                try {
                    data.write(filler.charAt(i));
                    Thread.sleep(new Random().nextInt(3000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
