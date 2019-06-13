package leetcode;

/**
 * Created by adimn on 2019/6/6.
 */
public class L7IntegerReverse {
    public int reverse(int x) {
        String str = x + "";
        StringBuilder res = new StringBuilder();
        boolean first = true;
        boolean negtive = false;
        if (str.startsWith("-")) {
            res.append("-");
            negtive = true;
        }
        for (int i = str.length() - 1; i > 0; i--) {
            char cur = str.charAt(i);
            if (first && "0".equals(cur)) {
                continue;
            } else {
                res.append(cur);
                first = false;
            }
        }
        if (!negtive) {
            res.append(str.charAt(0));
        }
        try {
            return Integer.parseInt(res.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public int reverseNew(int x) {
        int rev = 0;
        while (x != 0) {

            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            rev = rev * 10 + pop;


        }
        return rev;
    }

    public static void main(String[] args) {
        L7IntegerReverse ir = new L7IntegerReverse();
        System.out.println(ir.reverseNew(-123));
        System.out.println(ir.reverseNew(100));
        System.out.println(ir.reverseNew(1534236469));
//        System.out.println(ir.reverse(9646324351));
    }
}
