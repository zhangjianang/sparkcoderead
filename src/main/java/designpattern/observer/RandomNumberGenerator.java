package designpattern.observer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by adimn on 2019/6/18.
 */
public class RandomNumberGenerator extends NumberGenerator {
    private int number;
    private Random random;

    public RandomNumberGenerator(){
        super(new ArrayList<Observer>());
        random = new Random();
        number = 0;
    }

    public int getNumber() {
        return number;
    }

    public void execute(int n) {
        for(int i=0;i<n;i++){
            number = random.nextInt(30);
            notifyObserver(this);
        }
    }

    public static void main(String[] args) {
        Observer od =new DigitObserver();
        Observer og = new GraphObserver();
        Observer oc = new ConcreteObserver();

        RandomNumberGenerator rn = new RandomNumberGenerator();
        rn.addObserver(oc);
        rn.addObserver(od);
        rn.addObserver(og);

        rn.execute(10);
    }
}
