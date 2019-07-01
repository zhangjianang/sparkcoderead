package multithread.c7permessage.practice;

/**
 * Created by adimn on 2019/6/27.
 */
public class BlackHole {
    public static void enter(Object obj){
        System.out.println("setp 1");
//        magic(obj);
        joinmagic(obj);
        System.out.println("setp 2");
        synchronized (obj){
            System.out.println("step 3 never execute");
        }
    }
    public static void magic(final Object obj){
         Thread thread = new Thread(){
            public void run() {
                synchronized (obj) {
                    synchronized (this) {
                        Thread.currentThread().setName("got lock");
                        this.notifyAll();
                    }
                    while (true) {
                    }
                }
            }
        };

//      特別注意，直接启动新的Thread，新线程可能还没有获得obj的锁，主线程就获取了
//        所以需要让magic方法等待一会儿，确定新线程获取object锁才行。
        synchronized (thread){
            thread.setName("");
            thread.start();
            while("".equals(thread.getName())){
                try {
                    thread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("begin");
        Object obj =new Object();
        BlackHole.enter(obj);
        System.out.println("end");
    }

    public static void joinmagic(final Object obj){
         Thread thread = new Thread(){
            public void run(){
                synchronized (obj){
//                    这里获取外部object锁后，可以通知自己继续向下执行了。
                    synchronized (this){
                        notifyAll();
                    }
//                    让自己去等待 自己 执行完成，所以会一直等待下去。
                    try {
                        this.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        synchronized (thread){
            thread.start();
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

