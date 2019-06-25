package multithread.c6readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by adimn on 2019/6/25.
 */
public class DataBase<K,V> {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readlock = readWriteLock.readLock();
    private Lock writelock = readWriteLock.writeLock();

    private Map<K,V> map = new HashMap<K,V>();

    public void clear(){
        try {
            writelock.lock();
            verySlowly();
            map.clear();
        }finally {
            writelock.unlock();
        }
    }

    public  void put(K key,V value){
        try {
            writelock.lock();
            slowly();
            map.put(key, value);
        }finally {
            writelock.unlock();
        }
    }

    public  V get(K key){
        try {
            readlock.lock();
            verySlowly();
            return map.get(key);
        }finally {
            readlock.unlock();
        }
    }

    private void slowly(){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void verySlowly(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
