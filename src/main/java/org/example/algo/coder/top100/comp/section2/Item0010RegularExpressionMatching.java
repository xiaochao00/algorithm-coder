package org.example.algo.coder.top100.comp.section2;

public class Item0010RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new Item0010RegularExpressionMatching().new Solution();
        boolean res = solution.isMatch("aaa", "a*a");
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isMatch(String s, String p) {
            // dp方式求解
            boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
            dp[0][0] = true;
            for (int i = 0; i <= s.length(); i++) {
                for (int j = 1; j <= p.length(); j++) {
                    if ('*' == p.charAt(j-1)) {
                        if (matches(s, p, i, j - 1)) {
                            dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                        } else {
                            dp[i][j] = dp[i][j - 2];
                        }
                    } else {
                        if (matches(s, p, i, j)) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
            return dp[s.length()][p.length()];
        }

        private boolean matches(String s, String p, int i, int j) {
            if (i == 0) {
                return false;
            }
            return s.charAt(i - 1) == p.charAt(j - 1) || '.' == p.charAt(j - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}