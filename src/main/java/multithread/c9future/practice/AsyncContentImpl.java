package multithread.c9future.practice;

import java.io.DataInputStream;
import java.net.URL;

/**
 * Created by adimn on 2019/7/3.
 */
public class AsyncContentImpl implements Content {

    private byte[] contentbytes;
    private boolean ready = false;

    public synchronized byte[] getBytes() {
        while (!ready){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return contentbytes;
    }

    public AsyncContentImpl(final String urlstr){
        new Thread(){
            public void run(){
                try {
                    StringBuilder sb = new StringBuilder();
                    System.out.println(Thread.currentThread().getName() + " getting " + urlstr);
                    URL url = new URL(urlstr);
                    DataInputStream inputStream = new DataInputStream(url.openStream());
                    byte[] buffer = new byte[1024];
                    int index = 0;
                    try {
                        while ((index = inputStream.read(buffer)) > 0) {
                            sb.append(new String(buffer, 0, index));
                        }
                    } catch (Exception e) {

                    } finally {
                        inputStream.close();
                    }
                    setContentByte(sb.toString().getBytes());
//                    contentbytes = sb.toString().getBytes();
                    System.out.println(sb.toString().getBytes().length);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private synchronized void setContentByte(byte[] contentbytes){
        if(ready){
            return;
        }
        this.contentbytes = contentbytes;
        this.ready = true;
        notifyAll();
    }
}
