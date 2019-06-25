package multithread.c6readwrite;

/**
 * Created by adimn on 2019/6/25.
 */
public class MyReadWriteLock {
    private int readingReader=0;
    private int writingWriter=0;
    private int waitingWriter=0;
    private boolean writeFirst=true;

    public MyReadWriteLock(){}

    public synchronized void readLock() throws InterruptedException {
        while(writingWriter >0 || (writeFirst && waitingWriter >0)){
            wait();
        }
        readingReader++;
    }

    public synchronized void readUnlock(){
        if(readingReader>0) {
            readingReader--;
        }
        writeFirst = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriter++;
        try {
            while (readingReader > 0 || writingWriter > 0) {
                wait();
            }
        }finally {
            waitingWriter--;
        }
        writingWriter++;

    }

    public synchronized void writeUnlock(){
        if(writingWriter>0){
            writingWriter --;
        }
        notifyAll();
        writeFirst = false;
    }

}
