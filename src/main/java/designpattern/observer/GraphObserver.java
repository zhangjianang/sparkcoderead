package designpattern.observer;

/**
 * Created by adimn on 2019/6/18.
 */
public class GraphObserver implements Observer {
    public void update(NumberGenerator generator) {
        int num = generator.getNumber();
        for(int i=0;i<num;i++){
            System.out.print("*");
        }
        System.out.println();
    }
}
