package multithread.c11threadlocal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by adimn on 2019/7/4.
 */
public class TSLog {
    private PrintWriter printWriter;
    public TSLog(String filename){
        try {
            printWriter = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void println(String str){
        printWriter.write(str);
    }

    public void close(){
        printWriter.close();
    }
}
