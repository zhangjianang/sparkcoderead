package multithread.c8perworker;

import java.util.Random;

/**
 * Created by adimn on 2019/6/28.
 */
public class Request {
    private String name;
    private int num;
    private Random random = new Random();
    public Request(String name,int num){
        this.name = name;
        this.num = num;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName()+" execute "+this);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return name + " request NO."+num;
    }
}
