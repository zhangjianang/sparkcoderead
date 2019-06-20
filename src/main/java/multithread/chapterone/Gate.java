package multithread.chapterone;

/**
 * Created by adimn on 2019/6/19.
 */
public class Gate {
    private String name;
    private String address;

    private final Mutex mutex = new Mutex();

    public synchronized void pass(String name,String address){
        this.name = name;
        this.address = address;
        check();
    }

    public void mutexPass(String name,String address){
        mutex.lock();
        try {
            this.name = name;
            this.address = address;
            check();
        }catch (Exception e){

        }finally {
            mutex.unlock();
        }
    }

    public boolean check(){
        if(name.charAt(0) != address.charAt(0)){
            System.out.println("********* broken *********"+  toString());
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "name: "+name+", address: "+address;
    }

    public static void main(String[] args) {
        Gate gate  = new Gate();
        new Passer(gate,"ang","alabama").start();
        new Passer(gate,"lili","laker").start();
        new Passer(gate,"john","jeppsi").start();
    }
}

class Passer extends Thread{
    private final String name;
    private final String address;
    private final Gate gate;

    public  Passer(Gate gate, String name,String address){
        this.name = name;
        this.address = address;
        this.gate = gate;
    }


    @Override
    public void run() {
        System.out.println(name + " begin");
        while (true){
//            gate.pass(name,address);
            gate.mutexPass(name,address);
        }
    }
}

//wait 和 notify必须在synchronize 里面 ，否则报错 IllegalMonitorStateException

class Mutex{
    private boolean isuse;

    public Mutex(){
        isuse =false;
    }
    public synchronized void lock(){
        if(isuse){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isuse =true;
    }
    public synchronized void unlock(){
        isuse =false;
        notifyAll();
    }
}
