package leetcode;

/**
 * Created by adimn on 2019/6/4.
 */
public class Leet3LongestSub {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        String maxstr = "";
        char[] input = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < input.length ; i++){
            boolean subhas = false;
            char cur = input[i];
            int j=0;
            for(; j < sb.length();j++){
                if(cur == sb.charAt(j)){
                    subhas  = true;
                    break;
                }
            }
            if(!subhas){
                sb.append(cur);
            }else{
                if(sb.length()>max){
                    max = sb.length();
                    maxstr = sb.toString();
                }
                String res = sb.substring(j+1);
                sb = new StringBuilder();
                sb.append(res);
                sb.append(cur);
            }
        }
        if(sb.length()>max){
            max = sb.length();
            maxstr = sb.toString();
        }
        System.out.println(maxstr);
        return max;
    }

    public static void main(String[] args) {
        Leet3LongestSub l3 = new Leet3LongestSub();
        System.out.println(l3.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(l3.lengthOfLongestSubstring("pwwkewkea"));
        System.out.println(l3.lengthOfLongestSubstring("dvdf"));
    }
}
