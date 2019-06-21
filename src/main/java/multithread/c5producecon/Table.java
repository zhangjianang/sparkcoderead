package multithread.c5producecon;

/**
 * Created by adimn on 2019/6/21.
 */
public class Table {
    private final String[] buffer;
    private int count;
    private int nextput;
    private int nexttake;

    public Table(int num) {
        buffer = new String[num];
        count = 0;
        nextput = 0;
        nexttake = 0;
    }

    public synchronized void put(String sth) {
        try {
            while(count>=buffer.length){
                wait();
            }
            count++;
            buffer[nextput] = sth;
            nextput = (nextput + 1) % 3;
            notifyAll();
            System.out.println(Thread.currentThread().getName()+" produce "+sth);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized String take() {
        String info = null;
        try {
            while (count<=0) {
                wait();
            }
            count--;
            info = buffer[nexttake];
            nexttake = (nexttake+1)%3;

            notifyAll();
            System.out.println(Thread.currentThread().getName()+" consum "+ info);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return info;
    }
}
