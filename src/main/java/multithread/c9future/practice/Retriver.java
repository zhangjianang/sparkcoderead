package multithread.c9future.practice;

import java.io.IOException;

/**
 * Created by adimn on 2019/7/2.
 */
public class Retriver {
    public static Content retrieve(String url){
        Content syncContent = null;
        try {
//            syncContent = new SyncContentImpl(url);
            syncContent = new AsyncContentImpl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return syncContent;
        }
    }
}
