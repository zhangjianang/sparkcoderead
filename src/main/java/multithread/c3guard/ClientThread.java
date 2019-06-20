package multithread.c3guard;

/**
 * Created by adimn on 2019/6/20.
 */
public class ClientThread extends Thread{

    private RequestQueue requestQueue;

    private String  name;

    public ClientThread(String name, RequestQueue requestQueue){
        this.name = name;
        this.requestQueue = requestQueue;
    }

    public void run(){
        while (true){
            sendRequest();
        }
    }

    public void sendRequest(){
        for(int i=0;true;i++){
            Request request = new Request("No."+i);
            System.out.println(name+" send Request"+request);
            requestQueue.addRequest(request);
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
