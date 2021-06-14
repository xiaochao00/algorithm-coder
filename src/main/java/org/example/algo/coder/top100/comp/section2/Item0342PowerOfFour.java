package org.example.algo.coder.top100.comp.section2;

public class Item0342PowerOfFour {
    public static void main(String[] args) {
        Solution solution = new Item0342PowerOfFour().new Solution();
        boolean res = solution.isPowerOfFour(-240);
        System.out.println(res);

        res = solution.isPowerOfFour(5);
        System.out.println(res);

        res = solution.isPowerOfFour(8);
        System.out.println(res);

        res = solution.isPowerOfFour(16);
        System.out.println(res);

        res = solution.isPowerOfFour(32);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPowerOfFour(int n) {
            int oldN = n;
            // 特殊情况
            if (n == 0) {
                return false;
            }
            if (n == 1) {
                return true;
            }
            if ((n & 1) == 1) {
                return false;
            }
            // 后面连续0的数目
            int count = 0;
            while (n != 0) {
                int flag = n & 1;
                if (flag == 1) {
                    break;
                }
                count++;
                n = n >> 1;
            }
            //
            return (count % 2 == 0) && (1 << count) == oldN;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}