package leetcode;

/**
 * Created by adimn on 2019/6/10.
 */
public class L9IntPlainDromic {
    public boolean isPalindrome(int x) {

        if(x<0 || ( x>0 && x %10 ==0)){
            return false;
        }
        String str = x+"";
        int len = str.length();
        int index = len / 2;
        for(int i=0;i<index;i++){
            if(str.charAt(i) !=str.charAt(len - i-1) ){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        L9IntPlainDromic ipd = new L9IntPlainDromic();
//        System.out.println(ipd.isPalindrome(10));
//        System.out.println(ipd.isPalindrome(-101));
//        System.out.println(ipd.isPalindrome(101));
//        System.out.println(ipd.isPalindrome(3553));
//        System.out.println(ipd.isPalindrome(9));
        System.out.println(ipd.isPalindrome(-0));
//        System.out.println(ipd.isPalindrome(-1));
//        System.out.println(ipd.isPalindrome(0));
    }
}
