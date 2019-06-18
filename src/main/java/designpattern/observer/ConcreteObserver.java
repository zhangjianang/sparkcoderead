package designpattern.observer;

/**
 * Created by adimn on 2019/6/18.
 */
public class ConcreteObserver implements Observer {
    public void update(NumberGenerator generator) {
        int number = generator.getNumber();
        for(int i=0;i<number;i++){
            System.out.print("-");
        }
        System.out.println();
    }
}
