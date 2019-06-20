package multithread.c3guard;

/**
 * Created by adimn on 2019/6/20.
 */
public class ServerThread extends Thread {

    private String name;
    private RequestQueue requestQueue;
    public ServerThread(String name,RequestQueue requestQueue){
        this.name = name;
        this.requestQueue = requestQueue;
    }

    public void run(){
        while (true){
            dealRequest();
        }
    }

    public void dealRequest(){
        Request request = requestQueue.removeRequest();
        System.out.println(name +" handle request "+request);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread("ang",requestQueue).start();
//        new ClientThread("lili",requestQueue).start();
        new ServerThread("tomcat",requestQueue).start();
    }
}
