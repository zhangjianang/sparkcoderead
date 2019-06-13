package leetcode;

/**
 * Created by adimn on 2019/6/13.
 */
public class L10WildCard {
    public boolean isMatch2(String s, String p) {
        StringBuilder sp = new StringBuilder();
        int i = 0,j = 0;
        for1:
        while (i < s.length()) {
            boolean in = false;
            while (j < p.length() && i < s.length()) {
                in = true;
                char curs = s.charAt(i);
                char curp = p.charAt(j);
                if (curs == curp || '.' == curp) {
                    j++;
                    i++;
                    sp.append(curs);
                } else if ('*' == curp) {
                    if (sp.length() > 0) {
                        if (sp.charAt(sp.length() - 1) == curs || j > 0 && '.' == p.charAt(j - 1)) {
                            if(p.length()-j <=s.length()-i ) {
                                i++;
                                sp.append(curs);
                            }else if(p.length()-j-s.length()+i>1){
                                j++;
                                i--;
                            }else{
                                j++;
                            }
                        } else {
                            j++;
                        }
                    } else {
                        break for1;
                    }
                } else if (j + 1 < p.length() && '*' == p.charAt(j + 1)) {
                    j += 2;
                } else {
                    break for1;
                }
            }
            if (!in) {
                break;
            }
        }
        System.out.println(sp.toString());
        if (sp.length() > 0 && j == p.length() && i == s.length()) {
            return true;
        } else if (p.endsWith("*") && j == p.length() - 1) {
            return true;
        }
        return false;
    }

    public boolean isMatch(String s, String p){
        int slen = s.length(),plen = p.length();
        //函数自动初始化全部为false
        //dp[i][j] 代表s的前j个元素和p的前j个元素match情况
        //其实整个过程都是在更新dp数组，其实是试探性地把每一个配对都测试了【A】。
        boolean[][]  dp = new boolean[slen+1][plen+1];
        //dp[0][0]必然是true
        dp[0][0] = true;
        //排除一种特殊情况
        if(plen>0&&p.charAt(0)=='*') return false;
        //当i=0,dp[i][j]主要根据p的情况
        for(int j=1;j<=plen;j++){
            //下面的程序都是用i，j表示第i个数,从1开始
            //只有出现了*我们才能判断是否能出现匹配因为不是*的话s第0个和p的第j个是不可能匹配的
            //没字符和有字符怎么可能匹配呢？
            if(p.charAt(j-1)=='*'){
                //这里出现了*说明可以匹配0，1[>=2的情况肯定是false的因为s这时候没有轮到字符]
                //等于0个的时候被匹配的p是要倒退2个的，等于1个的时候是要倒退1个的
                dp[0][j] = dp[0][j-1]||dp[0][j-2];
            }
        }
        //上面都是在初始化整个dp数组的dp[0][j]行,事实上是dp[i][0]都是false的
        //开启非边缘值的更新
        //核心是对*代表什么的判定
        for(int i=1;i<=slen;i++){
            for(int j=1;j<=plen;j++){
                //匹配到了正常字符
                if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.') dp[i][j] = dp[i-1][j-1];
                if(p.charAt(j-1)=='*'){
                    //代表0个的时候
                    dp[i][j] = dp[i][j-2];
                    //代表1个的时候
                    //这里的或运算只是在告诉我们这个节点只要存在一种是true，无论我们进行多少此检测这个点的最终结果是true
                    dp[i][j] = dp[i][j]||dp[i][j-1];
                    //代表2个及以上,这个时候我们要确保最后的字符是相同的
                    if(s.charAt(i-1)==p.charAt(j-2)||p.charAt(j-2)=='.'){

                        dp[i][j] = dp[i][j]||dp[i-1][j];//这里为什么是j，因为代表k个，现在是k-1个，依然是‘*’来进行替代
                    }
                }
            }
        }
        return dp[slen][plen];
    }

}
