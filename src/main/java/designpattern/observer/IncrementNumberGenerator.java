package designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adimn on 2019/6/18.
 */
public class IncrementNumberGenerator extends NumberGenerator{
    private int number;
    private int start;
    private int end;
    private int step;

    public IncrementNumberGenerator(int start ,int end,int step) {
        super(new ArrayList<Observer>());
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public int getNumber() {
        return number;
    }

    public void execute(int n) {
        for(int i=start;i<end;i+=step){
            number = i;
            notifyObserver(this);
        }
    }

    public static void main(String[] args) {
        Observer od =new DigitObserver();
        Observer og = new GraphObserver();

        IncrementNumberGenerator icg = new IncrementNumberGenerator(10,50,5);
        icg.addObserver(od);
        icg.addObserver(og);
        icg.execute(0);
    }
}
