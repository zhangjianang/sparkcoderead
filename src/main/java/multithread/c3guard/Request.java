package multithread.c3guard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by adimn on 2019/6/20.
 */
public class Request {
    String name;
    public Request(String name){
        this.name = name;
    }

    public String toString(){
        return "[ request "+name+" ]";
    }
}

class RequestQueue{
    private final Queue<Request> requests ;

    public RequestQueue(){
        requests = new LinkedList<Request>();
    }

    public synchronized void addRequest(Request request){
        notifyAll();
        requests.offer(request);
    }

    public synchronized Request removeRequest(){
//        guard wait 不是进行忙等，使用yield会进行忙等，这时候没有让出锁。
//        什么是guard wait 呢，就是等待完成之后，还要再去判断临界区状态，这里要用if就没有这个情况了，被唤醒之后，直接执行了。
        while ( requests.size()==0 ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return requests.remove();
    }
}

class MyBlockQueue{

//    private final BlockingQueue<Request> blockingQueue = new LinkedBlockingDeque<Request>();
    public MyBlockQueue(){

    }
}
