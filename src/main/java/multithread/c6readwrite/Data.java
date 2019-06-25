package multithread.c6readwrite;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by adimn on 2019/6/25.
 */
public class Data {
    private char[] buffer;
//    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//    private final Lock readLock = readWriteLock.readLock();
//    private final Lock writeLock = readWriteLock.writeLock();

    private final MyReadWriteLock readWriteLock = new MyReadWriteLock();

    public Data(int size){

        buffer = new char[size];
        for(int i=0;i<size;i++){
            buffer[i] = '*';
        }
    }

    public String read() throws InterruptedException {
        readWriteLock.readLock();
        try {
            return doRead();
        }finally {
            readWriteLock.readUnlock();
        }
    }

    private String doRead(){
        StringBuilder sb = new StringBuilder();
         for(int i=0;i<buffer.length;i++){
             sb.append(buffer[i]);
         }
        slowly();
        System.out.println(Thread.currentThread().getName()+" reads "+sb.toString());
        return sb.toString();
    }

    private void slowly(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write(char input) throws InterruptedException {
        //            writeLock.lock();
        readWriteLock.writeLock();
        try{
            for(int i=0;i<buffer.length;i++){
                buffer[i] = input;
            }
            System.out.println(Thread.currentThread().getName()+" writing "+ new String(buffer));
            slowly();
        }finally {
//            writeLock.unlock();
            readWriteLock.writeUnlock();
        }
    }

}
