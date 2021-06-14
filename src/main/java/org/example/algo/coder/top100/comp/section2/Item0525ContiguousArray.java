package org.example.algo.coder.top100.comp.section2;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class Item0525ContiguousArray {
    public static void main(String[] args) {
        Solution solution = new Item0525ContiguousArray().new Solution();
        int res = solution.findMaxLength2(new int[]{0, 0, 1, 0, 0, 0, 1, 1});
        System.out.println(res);
        Assert.assertEquals(6, res);

        res = solution.findMaxLength2(new int[]{1, 1, 0, 0, 0, 1, 0, 0});
        System.out.println(res);
        Assert.assertEquals(6, res);

        res = solution.findMaxLength2(new int[]{0, 1, 1, 0, 1, 1, 1, 0});
        System.out.println(res);
        Assert.assertEquals(4, res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMaxLength(int[] nums) {
            // 对每一列j，dp[i]表示i-j的子数组的和
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int maxLen = 0;
            // 第j列，
            for (int j = 1; j < nums.length; j++) {
                for (int i = 0; i < j; i++) {
                    if (i == 0) {
                        dp[i] = dp[i] + nums[j];
                    } else {
                        dp[i] = dp[i - 1] - nums[i - 1];
                    }

                    if ((j - i + 1) % 2 == 0 && dp[i] == (j - i + 1) / 2) {
                        if (maxLen < dp[i]) {
                            maxLen = dp[i];
                        }
                    }

                }
            }
            return maxLen * 2;
        }

        public int findMaxLength2(final int[] nums) {
            int maxLen = 0;
            // 先求和
            Map<Integer, Integer> sumIndexMap = new HashMap<>();
            sumIndexMap.put(0, -1);
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i] == 1 ? 1 : -1;
                if (sumIndexMap.containsKey(sum)) {
                    int preIndex = sumIndexMap.get(sum);
                    if (i - preIndex > maxLen) {
                        maxLen = i - preIndex;
                    }
                } else {
                    sumIndexMap.put(sum, i);
                }
            }
            return maxLen;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}