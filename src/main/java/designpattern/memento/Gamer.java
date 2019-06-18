package designpattern.memento;

import java.util.List;
import java.util.Random;

/**
 * Created by adimn on 2019/6/18.
 */
public class Gamer {
    private List fruits;
    private int money;
    private Random random;
    private String fruitsname;

    public int getMoney(){
        return money;
    }
    public void bet(){
        while(money >0){

        }
    }

    public Memento createMemento(){
        return new Memento(money,fruits);
    }

    public void restoreMemento(Memento mem){
        money = mem.getMoney();
        fruits = mem.getFruits();
    }
}
