package multithread.c7permessage;

import io.netty.util.concurrent.EventExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by adimn on 2019/6/26.
 */
public class Host {

    private Helper helper;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public Host(){
        this.helper = new Helper();
    }

    public void request(final int count, final char ch){
        System.out.println("request("+count+","+ch+") begin");
        new Thread(new Runnable() {
            public void run() {
                helper.deal(count,ch);
            }
        }).start();
        System.out.println("request("+count+","+ch+") end");
    }

    public void cacheRequest(final int count,final char ch){
        System.out.println("cacheRequest("+count+","+ch+") begin");
        executorService.execute(new Runnable() {
            public void run() {
                helper.deal(count,ch);
            }
        });
        System.out.println("cacheRequest("+count+","+ch+") end");
    }

    public void scheduleRequest(final int count,final char ch){
        System.out.println("scheduleRequest("+count+","+ch+") begin");
        scheduledExecutorService.schedule(
                new Runnable() {
                    public void run() {
                        helper.deal(count,ch);
                    }
                },
                3L,
                TimeUnit.SECONDS);
        System.out.println("scheduleRequest("+count+","+ch+") end");
    }



    public static void main(String[] args) {
        Host host  = new Host();
        System.out.println("main begin");
//        host.request(10,'a');
//        host.request(20,'b');
//        host.request(30,'c');


        host.cacheRequest(10,'D');
        host.cacheRequest(20,'E');
        host.cacheRequest(30,'F');

//        host.scheduleRequest(10,'A');
//        host.scheduleRequest(20,'B');
//        host.scheduleRequest(30,'C');



        System.out.println("main end");
    }
}
