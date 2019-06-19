package multithread.chapterone;

/**
 * Created by adimn on 2019/6/19.
 */
public class Tool {
    private final String name;
    public Tool(String name){
        this.name = name;
    }
    public String toString(){
        return " [ "+name+" ] ";
    }

    public static void main(String[] args) {
        Tool left = new Tool("勺子");
        Tool right = new Tool("叉子");
        new Eater("ang",left,right).start();
        new Eater("lili",right,left).start();
    }
}

class Eater extends Thread{
    private Tool leftHand;
    private Tool rightHand;

    private String name;

    public Eater(String name,Tool leftHand, Tool rightHand){
        this.name = name;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }
    public void run(){
        while (true){
            eat2();
        }
    }
    public void eat(){
        synchronized (leftHand){
            System.out.println(name+" 拿起左边 "+leftHand);
            synchronized (rightHand){
                System.out.println(name+" 拿起右边 "+rightHand);
                System.out.println(name+" 吃 恩恩 ");
                System.out.println(name+" 放下右边 "+rightHand);
            }
            System.out.println(name+" 放下左边 "+leftHand);
        }
    }

    public synchronized void eat2(){

            System.out.println(name+" 拿起左边 "+leftHand);

                System.out.println(name+" 拿起右边 "+rightHand);
                System.out.println(name+" 吃 恩恩 ");
                System.out.println(name+" 放下右边 "+rightHand);

            System.out.println(name+" 放下左边 "+leftHand);

    }
}
