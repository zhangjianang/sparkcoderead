package multithread;

/**
 * Created by adimn on 2019/6/19.
 */
public class PrintThread extends Thread{
    private String messasge;
    public PrintThread(String messasge){
        this.messasge = messasge;
    }
    public void run() {
        for (int i=0;i<100;i++){
            System.out.print(messasge);
        }
    }

    public static void main(String[] args) {

        new PrintThread("nice").start();
        new PrintThread("good").start();

    }
}
