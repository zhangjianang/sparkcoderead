package multithread.c11threadlocal;

/**
 * Created by adimn on 2019/7/4.
 */
public class Log {
    private ThreadLocal<TSLog> tsLogThreadLocal ;

    public Log(){
        tsLogThreadLocal = new ThreadLocal<TSLog>();
    }

    public TSLog getLog(){
        TSLog tsLog = tsLogThreadLocal.get();
        if(tsLog ==null){
            tsLog = new TSLog(Thread.currentThread().getName()+"_log.txt");
            tsLogThreadLocal.set(tsLog);
        }
        return tsLog;
    }

    public void println(String str){
        getLog().println(str);
    }

    public void close(){
        getLog().close();
    }
}
