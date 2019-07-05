package multithread.c11threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by adimn on 2019/7/4.
 */
public class MyMain {
    public static void main(String[] args) {
        Log log = new Log();
        new ClientThread("ang",log).start();
        new ClientThread("lili",log).start();
        new ClientThread("john",log).start();

        practice();
    }

    public static void singleLog(){
        System.out.println("main begin");
        for(int i=0;i<10;i++){
            SingleThreadLog.println(i+"");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SingleThreadLog.close();
        System.out.println("main end");
    }

    public static void practice(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        final Log log = new Log();
        for(int i=0;i<10;i++){
            Runnable runnable = new Runnable() {
                public void run(){
                    log.println("hello");
                    log.close();
                }
            };
            executorService.execute(runnable);
//            executorService.submit(runnable);
        }
    }
}
