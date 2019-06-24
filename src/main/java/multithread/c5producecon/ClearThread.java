package multithread.c5producecon;

/**
 * Created by adimn on 2019/6/24.
 */
public class ClearThread extends Thread {
    private Table table;

    public ClearThread(String name,Table table){
        super(name);
        this.table = table;
    }

    public void run(){
        try {
            while (true){

                Thread.sleep(1000);
                table.clear();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
