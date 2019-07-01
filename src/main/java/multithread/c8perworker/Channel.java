package multithread.c8perworker;

/**
 * Created by adimn on 2019/6/28.
 */
public class Channel {
    private Request[] requests ;
    private int count;
    private int head;
    private int tail;
    private WorkerTHread[] workers;

    public Channel(int num,int  wnum){
        requests = new Request[num];
        head = 0;
        tail=0;
        count = 0;
        workers = new WorkerTHread[wnum];
        for(int i=0;i<wnum;i++){
            workers[i] = new WorkerTHread("worker-"+i,this);
        }
    }

    public void startWorker(){
        for(int i =0;i<workers.length;i++){
            workers[i].start();
        }
    }

    public synchronized void addRequest(Request request){
        while (count==requests.length){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requests[head] = request;
        head = (head+1)%requests.length;

        count++;
        notifyAll();
    }

    public synchronized Request ExecuteRequest(){
        while(count==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = requests[tail];
        tail = (tail+1)%requests.length;
        count--;
        notifyAll();
        return request;
    }

}
