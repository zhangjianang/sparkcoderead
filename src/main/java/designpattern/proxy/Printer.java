package designpattern.proxy;

/**
 * Created by adimn on 2019/6/19.
 */
public class Printer implements Printable {
    private String name;

    public Printer(){
        heavyJob();
    }

    public void print(String str) {
        System.out.println("name is :"+name);
        System.out.println(str);
    }

    public void setPrintName(String name) {
        this.name = name;
    }

    public String getPrintName() {
        return name;
    }

    private void heavyJob(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
