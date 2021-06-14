package org.example.algo.coder.top100.comp.section2;

public class Item0009PalindromeNumber {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean r = solution.isPalindrome(121);
        System.out.println(r);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public boolean isPalindrome(int x) {
            if (x <= 0) {
                return false;
            }
            int res = 0;
            int preX = x;
            while (x != 0) {
                res = res * 10 + x % 10;
                x = x / 10;
            }
            return res == preX;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}