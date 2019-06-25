package multithread.c5producecon;

/**
 * Created by adimn on 2019/6/24.
 */
public class LazyThread extends Thread {

    private Table table;
    public LazyThread(String name,Table table){
        super(name);
        this.table = table;
    }

    @Override
    public void run() {
        while (true){
            synchronized (table){
                try {
//                    这里必须是 table的wait，直接调用wait，只会影响自身
                    table.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void sth(Long x) throws InterruptedException {
        if(x!=0){
            Object o = new Object();
            synchronized (o){
                o.wait(x);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("begin");
        sth(3000L);
        System.out.println("end");
    }
}
