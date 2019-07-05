package multithread.samephore;

import java.util.concurrent.Semaphore;

/**
 * Created by adimn on 2019/7/5.
 */
public class Sampractice {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for(int i=0;i<500;i++){
            new Traver(semaphore).start();
        }
    }
}

class Traver extends Thread{
    private Semaphore semaphore ;
    private static volatile int num =0;

    public Traver(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            semaphore.acquire();
            num++;
            System.out.println(Thread.currentThread().getName()+"正在登机"+num);
//            if(num%2==0){
//                Thread.sleep(1000);
//                System.out.println(Thread.currentThread().getName()+"拒绝登机"+num);
//            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
            System.out.println(Thread.currentThread().getName()+"登机完成");
        }
    }
}
