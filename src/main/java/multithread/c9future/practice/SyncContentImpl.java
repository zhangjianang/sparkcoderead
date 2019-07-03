package multithread.c9future.practice;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by adimn on 2019/7/2.
 */
public class SyncContentImpl implements Content {

    private byte[] contentbytes;

    private StringBuilder sb = new StringBuilder();

    public byte[] getBytes() {
        return contentbytes;
    }

    public SyncContentImpl(String urlstr) throws IOException {
        System.out.println(Thread.currentThread().getName()+" getting "+urlstr);
        URL url = new URL(urlstr);
        DataInputStream inputStream = new DataInputStream(url.openStream());
        byte[] buffer =new byte[1024];
        int index=0;
        try {
            while ((index= inputStream.read(buffer))>0) {
                sb.append(new String(buffer,0,index));
            }
        }catch (Exception e){

        }finally {
            inputStream.close();
        }
        contentbytes = sb.toString().getBytes();
        System.out.println(contentbytes.length);
    }

}
