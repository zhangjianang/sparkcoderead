package multithread.c5producecon;

/**
 * Created by adimn on 2019/6/24.
 */
public class Host {

    public static void execute(int n) throws InterruptedException {
        for(int i= 0;i<n;i++){
            if(Thread.interrupted()){
                throw new InterruptedException(" execute interrupted ");
            }
            doHeavyJob( i );
        }
    }

    public static void doHeavyJob(int i){
        Long start = System.currentTimeMillis();
        while(start+100>System.currentTimeMillis()){

        }
        System.out.println("do heavy job_"+i+"end");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Host.execute(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

}
