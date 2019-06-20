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

//        第一种改变顺序
//        new Eater("ang",left,right).start();
//        new Eater("lili",left,right).start();

//        第二种 同时获取互斥资源
        Pair pair = new Pair(left,right);
        new Eater("john",pair).start();
        new Eater("harry",pair).start();
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

    public Eater(String name,Pair pair){
        this.name = name;
        this.leftHand = pair.getLeft();
        this.rightHand = pair.getRight();
    }

    public void run(){
        while (true){
            eat();
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
        Thread.yield();
    }

//    错误 这里的互斥资源变成了 eater，不是勺子和叉子了，所以会出现 两个人都持有勺子  这种情况
    public synchronized void eat2(){

            System.out.println(name+" 拿起左边 "+leftHand);

                System.out.println(name+" 拿起右边 "+rightHand);
                System.out.println(name+" 吃 恩恩 ");
                System.out.println(name+" 放下右边 "+rightHand);

            System.out.println(name+" 放下左边 "+leftHand);

    }
}

class Pair{
    private Tool spoon;
    private Tool fork;
    public Pair(Tool spoon,Tool fork){
        this.spoon = spoon;
        this.fork = fork;
    }
    public Tool getLeft(){
        return spoon;
    }
    public Tool getRight(){
        return fork;
    }
}
