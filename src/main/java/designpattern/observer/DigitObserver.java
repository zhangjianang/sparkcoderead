package designpattern.observer;

/**
 * Created by adimn on 2019/6/18.
 */
public class DigitObserver implements Observer {
    public void update(NumberGenerator generator) {
        System.out.println(generator.getNumber());
    }
}
