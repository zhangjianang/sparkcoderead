package multithread.c7permessage.practice;

/**
 * Created by adimn on 2019/6/27.
 */
public class Service {
    public void service() throws InterruptedException {
        System.out.println("service begin");
        for(int i = 0;i<100;i++) {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName()+" doing service "+ i);
        }
        System.out.println("service done");
    }

}
