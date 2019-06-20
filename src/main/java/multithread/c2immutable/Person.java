package multithread.c2immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by adimn on 2019/6/20.
 */
public final class Person {
//    final修饰的字符串只能在 声明时候，或构造函数中赋初值
//    static类型的只能在 声明时候，或者静态初始化块中赋初值
    private final String name;
    private final String address;


    public static final String tname;

    static {
        tname="super";
    }

    public Person(String name,String address){
        this.name = name;
        this.address = address;

    }

    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }

    public String toString(){
        return "name is: "+name+" , address is : "+address;
    }

}

class PrintPerson extends Thread{
    private Person person;
    public PrintPerson(Person person){
        this.person = person;
    }
    public void run(){
        while (true){
            System.out.println(Thread.currentThread().getName() +" print "+person);
        }
    }

    public static void main(String[] args) {
//        Person p = new Person("ang","alabama");
//        new PrintPerson(p).start();
//        new PrintPerson(p).start();
//        new PrintPerson(p).start();
//        直接使用list会报ConcurrentModificationException。或者nullpointer异常
//        final List sth = new ArrayList();

//        使用synchronizedList 需要配合 synchronize关键字一起。在
//        final List sth = Collections.synchronizedList(new ArrayList());

        List sth = new CopyOnWriteArrayList();

        new WriteList(sth).start();

        new ReadList(1,sth).start();
        new ReadList(0,sth).start();

    }
}

class WriteList extends Thread{
    private List list;

    public WriteList(List list){
        super("writethread");
        this.list = list;
    }

    public void run(){
        for(int i=0;true;i++){
            list.add(i);
        }
    }
}

class ReadList extends Thread{
    private List list ;
    public ReadList(int num,List list){
        super("readthread--"+num);
        this.list = list;
    }
    public void run(){
        while (true) {
            synchronized (list) {
                for (int i=0;i<list.size();i++) {
                    System.out.println(Thread.currentThread().getName()+" read：" + list.get(i));
                    if(i%1000==0){
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
