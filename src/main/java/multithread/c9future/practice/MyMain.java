package multithread.c9future.practice;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by adimn on 2019/7/2.
 */
public class MyMain {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Content c1 = Retriver.retrieve("https://www.baidu.com/");
        Content c2 = Retriver.retrieve("https://www.sina.com.cn/");
        Content c3 = Retriver.retrieve("http://www.youku.com/");

        saveToFile("baidu.html",c1);
        saveToFile("sina.html",c2);
        saveToFile("youku.html",c3);
    }

    private static void saveToFile(String filename,Content content){
        byte[] bytes = content.getBytes();
        System.out.println("save : "+ bytes.length);
        try {
            FileOutputStream out = new FileOutputStream(filename);
            for(int i=0;i<bytes.length;i++){
                out.write(bytes[i]);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
