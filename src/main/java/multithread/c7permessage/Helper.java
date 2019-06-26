package multithread.c7permessage;

/**
 * Created by adimn on 2019/6/26.
 */
public class Helper {

    public void deal(int num,char ch){
        System.out.println("deal ("+num+","+ch+") begin");
        for(int i = 0;i<num;i++){
            System.out.print(ch);
            slowly();
        }
        System.out.println();
        System.out.println("deal ("+num+","+ch+") end");
    }
    public void slowly(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
