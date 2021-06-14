package org.example.algo.coder.top100.comp.section1;

/**
 * Item0005LongestPalindromicSubstring
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/23 19:11
 */
public class Item0005LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new Item0005LongestPalindromicSubstring().new Solution();
        String res = solution.longestPalindrome("babad");
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            int size = s.length();
            int[] flag = new int[size];

            int startIndex = 0;
            int endIndex = 0;
            int maxLen = 1;
            //
            for (int column = 1; column < size; column++) {
                flag[column] = 1;
                for (int row = 0; row < column; row++) {
                    if (s.charAt(column) != s.charAt(row)) {
                        // 不相同，
                        flag[row] = 0;
                    } else {
                        if ((column - row) == 1) {
                            flag[row] = 1;
                        } else {
                            flag[row] = flag[row + 1];
                        }
                        //
                        if (flag[row] == 1) {
                            int curLen = column - row + 1;
                            if (curLen > maxLen) {
                                maxLen = curLen;
                                startIndex = row;
                                endIndex = column;
                            }
                        }
                    }
                }
            }
            //
            return s.substring(startIndex, endIndex + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
