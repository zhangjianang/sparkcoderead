package multithread.c11threadlocal;


/**
 * Created by adimn on 2019/7/4.
 */
public class ClientThread extends Thread {
    private Log log;
    public ClientThread(String name,Log log){
        super(name);
        this.log = log;
        this.log.println("sh");
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" print "+i);
            log.println(i+"");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.close();
    }
}
