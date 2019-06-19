package designpattern.proxy;

/**
 * Created by adimn on 2019/6/19.
 */
public class PrinterProxy implements Printable {
    private String name;
    private Printer real;

    public void print(String str) {
        realize();
        real.print(str);
    }

    public void setPrintName(String name) {
        this.name = name;
    }

    public String getPrintName() {
        return name;
    }

    private void realize(){
        if(real == null){
            real = new Printer();
        }
        real.setPrintName(name);
    }

    public static void main(String[] args) {
        PrinterProxy pp = new PrinterProxy();
        pp.setPrintName("ang");
        pp.print("hello,world");
    }

}
