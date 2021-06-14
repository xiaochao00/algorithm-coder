package org.example.algo.coder.top100.comp.section2;

import java.util.Arrays;

public class Item0494TargetSum {
    public static void main(String[] args) {
        Solution solution = new Item0494TargetSum().new Solution();
        int res = solution.findTargetSumWays(new int[]{0, 0, 1}, 1);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            return sumWays(nums, target);
        }

        private int sumWays(int[] nums, int target) {
            // 递归的方式求解
            if (nums.length == 1) {
                if (nums[0] == target && target == 0) {
                    return 2;
                } else if (nums[0] == target || nums[0] == -target) {
                    return 1;
                } else {
                    return 0;
                }
            }
            int[] subNums = Arrays.copyOfRange(nums, 0, nums.length - 1);
            int way1 = sumWays(subNums, target - nums[nums.length - 1]);
            int way2 = sumWays(subNums, target + nums[nums.length - 1]);
            return way1 + way2;
        }

        public int findTargetSumWays2(int[] nums, int target) {
            // 动态规划方式求解
            
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}