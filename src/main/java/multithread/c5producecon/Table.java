package multithread.c5producecon;

/**
 * Created by adimn on 2019/6/21.
 */
public class Table {
    private final String[] buffer;
    private int count;
    private int head;
    private int tail;

    public Table(int num) {
        buffer = new String[num];
        count = 0;
        head = 0;
        tail = 0;
    }

    public synchronized void put(String sth) {
        try {
            while(count>=buffer.length){
                System.out.println(Thread.currentThread().getName()+" put wait");
                wait();
            }
            count++;
            buffer[head] = sth;
            head = (head + 1) % 3;
            notify();
            System.out.println(Thread.currentThread().getName()+" produce "+sth);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized String take() {
        String info = null;
        try {
            while (count<=0) {
                System.out.println(Thread.currentThread().getName()+" take wait");
                wait();
            }
            count--;
            info = buffer[tail];
            tail = (tail +1)%3;

            notifyAll();
            System.out.println(Thread.currentThread().getName()+" consum "+ info);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return info;
    }

    public synchronized void clear(){
        while (count > 0 ){
            String cake = buffer[tail];
            tail = (tail +1)%buffer.length;
            System.out.println(Thread.currentThread().getName() + " clear "+cake);
            count --;
        }
        count = 0;
        tail = 0;
        head = 0;
        notify();
    }
}
