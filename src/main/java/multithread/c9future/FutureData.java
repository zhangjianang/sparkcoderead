package multithread.c9future;


/**
 * Created by adimn on 2019/7/2.
 */
public class FutureData  implements Data {

    private RealData realData;
    private boolean ready = false;

    public synchronized String getContent() {
       while (!ready){
           try {
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
        return realData.getContent();
    }

    public synchronized void setRealData(RealData realData){
        if(ready){
            return;
        }
        this.realData = realData;
        ready =true;
        notifyAll();
    }
}
