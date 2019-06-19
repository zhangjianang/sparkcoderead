package designpattern.memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by adimn on 2019/6/18.
 */
public class Gamer {
    private List fruits;
    private int money;
    private Random random;
    private String[] fruitsname = {"苹果","橘子","香蕉","樱桃"};

    public Gamer(int money){
        this.money = money;
        fruits = new ArrayList();
        random = new Random();
    }

    public int getMoney(){
        return money;
    }
    public void bet(){

        int nextInt = (random.nextInt(6) +1)%6;
        if(nextInt == 1){
            money += 100;
            System.out.println("金钱增加");
        }else if(2 == nextInt){
            money /=2;
            System.out.println("金钱减半");
        }else if(0 == nextInt) {
            String fruit = getFruit();
            System.out.println("获得水果:"+fruit);
            fruits.add(fruit);
        }else {
            System.out.println("什么都没发生");
        }

    }

    private String getFruit(){

        return fruitsname[random.nextInt(fruitsname.length)];
    }

    public Memento createMemento(){
        return new Memento(money,fruits);
    }

    public void restoreMemento(Memento mem){
        money = mem.getMoney();
        fruits = mem.getFruits();
    }

    public  String  toString(){
        return "money = "+ money+";fruit = "+fruits;
    }

    public static void main(String[] args) {
        Gamer gamer = new Gamer(400);
        Memento memento = gamer.createMemento();
        for(int i=0; i<10;i++){
            System.out.println("==="+i);
            gamer.bet();
            System.out.println("当前状态"+gamer);

            if(gamer.money > memento.getMoney()){
                memento = gamer.createMemento();
            }else if(gamer.getMoney() < memento.getMoney()){
                gamer.restoreMemento( memento);
            }
        }

    }
}
