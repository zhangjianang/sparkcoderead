package leetcode;

/**
 * Created by adimn on 2019/6/5.
 */
public class L5PalinDromic {

    public String longestPalindrome(String s) {

        String lonStr = "";
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            StringBuilder temp = new StringBuilder();
            if(lonStr.length()+i >chars.length){
                break;
            }
            for(int j=i;j<chars.length;j++){
                if(temp.length()+i >chars.length){
                    break;
                }
                temp.append(chars[j]);
                if(checkIsPalindrome(temp.toString()) &&temp.length()>lonStr.length()){
                    lonStr = temp.toString();
                }
            }
        }
        return lonStr;
    }
    private Boolean checkIsPalindrome(String str){
        if(str.length() == 0){
            return false;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        int index = len/2 +1;
        int i = 0 ;
        for(;i<index;i++){
            if(chars[i] != chars[len-i-1]){
                break;
            }
        }
        if(i == index){
            return true;
        }
        return  false;
    }

    public static void main(String[] args) {
        L5PalinDromic pd = new L5PalinDromic();
        System.out.println(pd.longestPalindrome("cbbd"));
        System.out.println(pd.longestPalindrome("a"));
        System.out.println(pd.longestPalindrome("cbbdccc"));
    }
}
