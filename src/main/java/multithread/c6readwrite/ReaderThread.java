package multithread.c6readwrite;

/**
 * Created by adimn on 2019/6/25.
 */
public class ReaderThread extends Thread {

    private Data data;

    public ReaderThread(String name,Data data){
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        while (true){
            try {
                data.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Data data = new Data(10);
        new ReaderThread("reader---1",data).start();
        new ReaderThread("reader---2",data).start();
        new ReaderThread("reader---3",data).start();
        new WriterThread("writer001",data,"we").start();
        new WriterThread("writer001",data,"are").start();
        new WriterThread("writer001",data,"ch").start();
    }
}
