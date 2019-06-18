package designpattern.observer;

import java.util.List;

/**
 * Created by adimn on 2019/6/18.
 */
public abstract class NumberGenerator {
    public NumberGenerator(List<Observer> observers){
        this.observers = observers;
    }

    private List<Observer> observers;

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void deleteObserver(Observer observer){
        for(Observer per:observers){
            if(observer==per){
                observers.remove(per);
            }
        }
    }

    public void notifyObserver(NumberGenerator numberGenerator ){
        for(Observer per:observers){
            per.update(numberGenerator);
        }
    }

    public abstract int getNumber();
    public abstract void execute(int n);
}
