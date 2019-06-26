package ioconn;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by adimn on 2019/6/26.
 */
public class ReadData {
    private String getTemplate(String fileName) {
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);
        try {
            return IOUtils.toString(inputStream, "utf8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        ReadData readData = new ReadData();
        String template = readData.getTemplate("/test/data.json");
        System.out.println(template);
    }
}
