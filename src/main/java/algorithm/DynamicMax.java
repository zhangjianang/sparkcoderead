package algorithm;

/**
 * Created by adimn on 2019/6/13.
 */
public class DynamicMax {
    public static int cut(int[] p,int n) {
       if(n ==0){
           return 0;
       }
        int q = Integer.MIN_VALUE;
        for(int i=1;i<= n;i++){
            q = Math.max(q,p[i-1]+cut(p,n-i));
        }
        return q;
    }

    public static void main(String[] args) {
//        int[] price = {1,5,8,9,10,17,17,20,24,30};
//        System.out.println(cut(price,1));
//        System.out.println(cut(price,2));
//        System.out.println(cut(price,5));
        System.out.println(fab(7));

        System.out.println(fabnacci(7));
        System.out.println(fabnacciBottom(7));
    }

    public static int fab(int n){
        if(n ==1 || n == 2){
            return 1;
        }
        return fab(n-1)+fab(n-2);
    }


    //    自顶向下的
    public static int fabnacci(int n){
        int []Memo=new int[n+1];
        for(int i=0;i<=n;i++)
            Memo[i]=-1;
        return memoFib(Memo,n);
    }


    public static int memoFib(int[] memo,int n){
        if(memo[n]>0){
            return memo[n];
        }
        if(n < 3){
            memo[n] = 1;
        }else {
            memo[n] = memoFib(memo, n - 1) + memoFib(memo, n - 2);
        }
        return memo[n];
    }
//    自底向上的

    public static int fabnacciBottom(int n){
        int[] memo = new int[n+1];
        for(int i=1;i<memo.length;i++){
            if(i<3){
                memo[i] = 1;
            }else {
                memo[i] = memo[i-1]+memo[i-2];
            }
        }
        return memoFibBottom(memo,n);
    }

    public static int memoFibBottom(int[] memo,int n){

        return memoFib(memo, n - 1) + memoFib(memo, n - 2);
    }
}
