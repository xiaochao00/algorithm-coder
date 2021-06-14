package org.example.algo.coder.top100.comp.section2;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class Item0523ContinuousSubarraySum {
    public static void main(String[] args) {
        Solution solution = new Item0523ContinuousSubarraySum().new Solution();
        boolean res = solution.checkSubarraySum2(new int[]{5, 0, 0, 0}, 3);
        Assert.assertTrue(res);
        System.out.println(res);

        res = solution.checkSubarraySum2(new int[]{23, 2, 4, 6, 7}, 6);
        Assert.assertTrue(res);
        System.out.println(res);

        res = solution.checkSubarraySum2(new int[]{23, 2, 6, 4, 7}, 6);
        Assert.assertTrue(res);
        System.out.println(res);

        res = solution.checkSubarraySum2(new int[]{23, 2, 6, 4, 7}, 13);
        Assert.assertFalse(res);
        System.out.println(res);

        res = solution.checkSubarraySum2(new int[]{23, 2, 4, 6, 6}, 7);
        Assert.assertTrue(res);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean checkSubarraySum2(int[] nums, int k) {
            // 如果两个数的差是k的倍数，那么说明这两个数除k的余数相同
            int[] sums = new int[nums.length];
            Map<Integer, Integer> remainderIndexMap = new HashMap<>((int) (nums.length / 0.75 + 2));
            remainderIndexMap.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                sums[i] = (i == 0 ? nums[i] : nums[i] + sums[i - 1]);
                int remainder = sums[i] % k;
                if (remainderIndexMap.containsKey(remainder)) {
                    int preIndex = remainderIndexMap.get(remainder);
                    if (i - preIndex >= 2) {
                        return true;
                    }
                } else {
                    remainderIndexMap.put(remainder, i);
                }
            }
            return false;
        }

        public boolean checkSubarraySum(int[] nums, int k) {
            int[] dp = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                dp[i] = nums[i];
                for (int j = i + 1; j < nums.length; j++) {
                    dp[j] = dp[j - 1] + nums[j];
                    if (dp[j] % k == 0) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}