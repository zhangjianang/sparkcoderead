package multithread.c9future;

/**
 * Created by adimn on 2019/7/2.
 */
public class Host {

    public Data request(final int num,final char ch){
        System.out.println("request ( "+num+", "+ch+") begin");
        final FutureData futureData = new FutureData();
        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(num,ch);
                futureData.setRealData(realData);
            }
        }.start();
        System.out.println("request ( "+num+", "+ch+") end");
        return futureData;
    }


    public static void main(String[] args) {
        Host host = new Host();
        try {
            Data rd1 = host.request(-1, 'A');
            System.out.println("data1 = " + rd1.getContent());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void invoke(){
        System.out.println("main begin");
        Host host = new Host();
        Data rd1 = host.request(10, 'A');
        Data rd2 = host.request(20,'B');
        Data rd3 = host.request(30,'C');
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main other task ");

        System.out.println("data1 = "+rd1.getContent());
        System.out.println("data2 = "+rd2.getContent());
        System.out.println("data3 = "+rd3.getContent());
        System.out.println("main end");
    }
}
