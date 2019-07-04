package multithread.c11threadlocal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by adimn on 2019/7/4.
 */
public class SingleThreadLog {
    private static PrintWriter writer;

    static{
        try {
            writer = new PrintWriter(new FileWriter("log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void println(String str){
        System.out.println("log write "+str);

        writer.write(str);
    }

    public static void close(){
        System.out.println("log end");
        writer.close();
    }
}
