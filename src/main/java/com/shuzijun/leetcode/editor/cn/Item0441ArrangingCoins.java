//你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。 
//
// 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 5
//输出：2
//解释：因为第三行不完整，所以返回 2 。
// 
//
// 示例 2： 
//
// 
//输入：n = 8
//输出：3
//解释：因为第四行不完整，所以返回 3 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 231 - 1 
// 
// Related Topics 数学 二分查找 
// 👍 137 👎 0


package com.shuzijun.leetcode.editor.cn;

public class Item0441ArrangingCoins {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.arrangeCoins(1);
        System.out.println(res);

        res = solution.arrangeCoins(2);
        System.out.println(res);

        res = solution.arrangeCoins(3);
        System.out.println(res);

        res = solution.arrangeCoins(4);
        System.out.println(res);

        res = solution.arrangeCoins(5);
        System.out.println(res);

        res = solution.arrangeCoins(6);
        System.out.println(res);

        res = solution.arrangeCoins(2147483647);
        System.out.println(res);

        res = solution.arrangeCoins2(1);
        System.out.println(res);
        res = solution.arrangeCoins2(2);
        System.out.println(res);
        res = solution.arrangeCoins2(3);
        System.out.println(res);
        res = solution.arrangeCoins2(4);
        System.out.println(res);
        res = solution.arrangeCoins2(5);
        System.out.println(res);
        res = solution.arrangeCoins2(6);
        System.out.println(res);
        res = solution.arrangeCoins2(2147483647);
        System.out.println(res);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int arrangeCoins(int n) {
            int lineNum = 0;
            int sum = n;
            for (int i = 1; i <= n; i++) {
                sum -= i;
                lineNum++;
                if (sum <= 0) {
                    break;
                }
            }

            if (0 == sum) {
                return lineNum;
            }

            return lineNum - 1;
        }

        public int arrangeCoins2(int n) {
            int lineNum = 0;
            for (int i = 1; i <= n ; i++) {
                long sum = i * (i + 1L) / 2;
                if (sum > n) {
                    return lineNum;
                } else if (sum == n) {
                    return lineNum + 1;
                }
                lineNum++;
            }
            return lineNum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}