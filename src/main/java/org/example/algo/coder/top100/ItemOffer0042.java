package org.example.algo.coder.top100;

/**
 * offer 0042
 * https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 *
 * @author shichao
 * @since 1.0.0
 * 2021/7/18 0:09
 */
public class ItemOffer0042 {
    public static void main(String[] args) {
        int res = maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(res);

        res = maxSubArray2(new int[]{1, -1, 1});
        System.out.println(res);
    }

    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        // 前缀和
        int[] sum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum[i] = (i == 0) ? nums[i] : (sum[i - 1] + nums[i]);
        }
        // 下面的双重循环会超过时间限制
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sumIj = (i == 0 ? sum[j] : (sum[j] - sum[i - 1]));
                if (sumIj > max) {
                    max = sumIj;
                }
            }
        }
        //
        return max;
    }

    public static int maxSubArray2(int[] nums) {
        //动态规划 dp[i],表示以dp[i]结尾的子数组最大和
        // dp[i]=max{dp[i-1]+nums[i],nums[i]}
        // 最终的结果为 max(dp[i])
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }
}
