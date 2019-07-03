package multithread.c9future;

/**
 * Created by adimn on 2019/7/2.
 */
public class RealData implements Data {
    private String content;

    public RealData(int num,char ch){
        byte[] bytes = new byte[num];
        System.out.println("make real data ( "+num+","+ch+" ) begin");
        content = "";
        for(int i=0;i<num;i++){
            content+=ch;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("make real data ( "+num+","+ch+" ) end");
    }
    public String getContent() {
        return content;
    }
}
