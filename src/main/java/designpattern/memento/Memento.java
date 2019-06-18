package designpattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adimn on 2019/6/18.
 */
public class Memento {
    public int money;
    private List fruits;

    public int getMoney(){
        return  money;
    }

    public List getFruits(){
        return fruits;
    }

    private void addFruit(String fruit){
        fruits.add(fruit);
    }

    public Memento(int money,List fruits){
        this.money  = money;
        this.fruits = fruits;
    }

    public Memento(int money){
        this.money = money;
        this.fruits = new ArrayList();
    }
}
