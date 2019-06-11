package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by adimn on 2019/6/6.
 */
public class L8StrToInt {
    private Set MYINT = new HashSet(){{add('9');add('8');add('7');add('6');add('5');add('4');add('0');add('1');add('2');add('3');}};

    public int myAtoi(String str) {
        str = str.trim();
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean hasop = false;
        for(int i=0;i<chars.length; i++) {
            char curchar = chars[i];
            if (!hasop) {
                if ('+' == curchar || '-' == curchar || MYINT.contains(curchar)) {
                    sb.append(curchar);
                    hasop = true;
                } else {
                    break;
                }
            } else {
                if (MYINT.contains(curchar)) {
                    sb.append(curchar);
                } else {
                    break;
                }
            }
        }
        System.out.println(sb.toString());
        if("+".equals(sb.toString())|| "-".equals(sb.toString()) || "".equals(sb.toString())){
            return 0;
        }else {
            try {
                return Integer.parseInt(sb.toString());
            }catch (NumberFormatException e){
                if(sb.charAt(0) == '-'){
                    return Integer.MIN_VALUE ;
                }
                return Integer.MAX_VALUE;
            }
        }
    }

    public static void main(String[] args) {
        L8StrToInt mi = new L8StrToInt();
//        System.out.println(mi.myAtoi("we +123 my old"));
        System.out.println(mi.myAtoi("-91283472332"));
        System.out.println(mi.myAtoi("    w  - 912834 72332"));
        System.out.println(mi.myAtoi("-+42"));
        System.out.println(mi.myAtoi("+-42"));
    }
}
