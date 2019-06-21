package multithread.c4balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by adimn on 2019/6/21.
 */
public class Data {
    private Boolean ischange;
    private String filename;
    private String content;

    public Data(String filename,String content){
        this.filename = filename;
        this.content = content;
        this.ischange = true;
    }

    public synchronized void change( String content){

        this.content = content;
        ischange = true;

    }

    public  void dosave() throws IOException {
        System.out.println(Thread.currentThread().getName()+" save "  +content);
        Writer writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }

    public  void save() throws IOException {
        if(!ischange){
            System.out.println(Thread.currentThread().getName()+" balking " );
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dosave();
        ischange = false;
    }

    public String getContent(){
        return content;
    }
}
